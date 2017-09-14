package com.koroc.evmap.sjs

import com.koroc.evmap.Model
import com.koroc.sjs.html.Attr._
import com.koroc.sjs.html._
import com.koroc.sjs.openlayers.ol
import com.koroc.sjs.openlayers.ol.Feature
import com.koroc.sjs.openlayers.ol.layer.Layer

import scala.collection.mutable.ListBuffer
import scala.scalajs.js

import org.scalajs.dom
import org.scalajs.dom._

/**
	* Map element, being container for ol.Map element.
	*/
class Map extends Div {
	attr(`class` := "mapview")

	var locations: List[Model.Location] = Nil
	var dirty = false

	def setLocations(l: List[Model.Location]): Unit = {
		locations = l
		dirty = true
	}

	var locationClickFn: (Model.Location) => Unit = null
	var infoPanel: InfoPanel = null

	val popupDiv = new Div {
		attr(id := "popup")
		add("POPUP")
		elt.onclick = (e: dom.Event)=>{
			println("popup click!")
			e.stopPropagation()
		}

		def updateForHover(l: Model.Location): Unit = {
			removeAllChildren()
			add(l.name)
		}

	}

	add(popupDiv)

	var trackingLocation: Model.Location = null // item that's being tracked
	var lat: Double = 50.0
	//52.08784
	var lng: Double = 5.05832
	val initialZoom = 6

	val _ol = js.Dynamic.global.ol
	var olMap: ol.Map = _
	var mapView: ol.View = _
	var popupOverlay: ol.Overlay = null

	class LocationFeature(val location: Model.Location, val feature: ol.Feature)

	val locationFeatures = new ListBuffer[LocationFeature]

	def featureOf(v: Model.Location) = locationFeatures.find(_.location == v) match {
		case Some(fv) => fv.feature
		case None => throw new Exception("Location not found")
	}

	def locationOf(f: ol.Feature): scala.Option[Model.Location] = locationFeatures.find(_.feature == f).map(_.location)

	val featureSource = ol.source.Vector()

	def updateFeatures(): Unit = {
		import ol.Attr._
		featureSource.clear(true)
		locationFeatures.clear()

		for (location <- locations) {
			val vlat = location.location.lat
			val vlng = location.location.lon

			val feature1 = ol.Feature(
				geometry := new ol.geom.Point(_ol.proj.transform(js.Array(vlng, vlat), "EPSG:4326", "EPSG:3857"))
			)
			feature1.setStyle(ol.style.Style(
				image := ol.style.Icon(
					src := s"img/map-icons-sander/${location.icon.name}.svg",
					anchor := js.Array(0.5,1.0)
					//ol.Attr.size := js.Array(100,100)
					//scale := 1.5
				)
			))

			featureSource.addFeature(feature1)
			locationFeatures += new LocationFeature(location, feature1)
		}
		dirty = false
	}

	def distance(x1: Double, y1: Double, x2: Double, y2: Double): Double = {
		val dx = x2-x1
		val dy = y2-y1
		Math.sqrt((dx*dx) + (dy*dy))
	}

	def update(): Unit = {
		if (dirty) updateFeatures()
		if (infoPanel != null) infoPanel.elt.style.visibility = "hidden"

		if (olMap == null) {
			import ol.Attr._
			import ol.{Attr => oa}

			val osmLayer = ol.layer.Tile(
				//source := ol.source.OSM()
				source := ol.source.MapQuest()
			)

			val featureLayer = ol.layer.Vector(
				source := featureSource
			)

			if (trackingLocation != null) {
				lat = trackingLocation.location.lat
				lng = trackingLocation.location.lon
			}

			mapView = ol.View(
				center := _ol.proj.transform(js.Array(lng, lat), "EPSG:4326", "EPSG:3857"),
				zoom := initialZoom,
				enableRotation := false
			)

			this.olMap = ol.Map(
				target := elt,
				layers := js.Array(osmLayer, featureLayer),
				view := mapView
			)

			this.popupOverlay = ol.Overlay(
				element := document.getElementById("popup"),
				positioning := "top-center",
				stopEvent := false
			)
			this.olMap.addOverlay(this.popupOverlay)

			// onClick
			if (true) {
				this.olMap.on("click", (e: js.Dynamic) => {
					//println(s"COORD: ${e.coordinate}")
					val lonlat = _ol.proj.transform(e.coordinate.asInstanceOf[js.Array[Double]], "EPSG:3857", "EPSG:4326").asInstanceOf[js.Array[Double]]
					val lon = lonlat(0)
					val lat = lonlat(1)
					//println(s"LON/LAT: ${lon} ${lat}")
					val pixel = e.pixel.asInstanceOf[ol.Pixel]

					//println(s"x=${pixel.x} y=${pixel.y}")
					var minDist: Double = -1
					var minLoc: Model.Location = null
					for (loc <- locations) {
						val d = distance(lat, lon, loc.location.lat, loc.location.lon)
						//println(s"($lat,$lon) (${loc.location.lat},${loc.location.lon})  $d  ${loc.name}")
						if (minLoc == null || d < minDist) {
							minLoc = loc
							minDist = d
						}
					}
					//println(s"MINDIST: ${minDist}")
					if (minLoc != null) {
						infoPanel.elt.style.visibility = "visible"
						infoPanel.update(minLoc)
					} else {
						infoPanel.elt.style.visibility = "hidden"
					}

				})
			}
			// onHover
			if (true) {
				this.olMap.on("pointermove", (e: js.Dynamic) => {
					val pixel = e.pixel.asInstanceOf[ol.Pixel]
					val feature = this.olMap.forEachFeatureAtPixel(pixel, (f: Feature, l: Layer) => {
						f
					})
					locationOf(feature) match {
						case Some(loc) =>
							popupDiv.updateForHover(loc)
							popupDiv.elt.style.visibility = "visible"
							popupOverlay.setPosition(feature.getGeometry().getCoordinates())
						case None =>
							popupDiv.elt.style.visibility = "hidden"
					}
				})
			}

		} else {
			// adjust
			if (trackingLocation != null) {
				lat = trackingLocation.location.lat
				lng = trackingLocation.location.lon
				mapView.setCenter(_ol.proj.transform(js.Array(lng, lat), "EPSG:4326", "EPSG:3857").asInstanceOf[js.Object])
			}
			for (location <- locations) {
				val vlat = location.location.lat
				val vlng = location.location.lon
				val feature1 = featureOf(location)
				feature1.getGeometry().setCoordinates(_ol.proj.transform(js.Array(vlng, vlat), "EPSG:4326", "EPSG:3857"))
			}
		}
	}

}

