package com.koroc.evmap.sjs

import com.koroc.evmap.{Context, Model}
import com.koroc.json
import com.koroc.json.JsValue
import com.koroc.sjs.{JsUtil, View}
import com.koroc.sjs.html.Attr._
import com.koroc.sjs.modal.{ModalAware, ModalManager}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import org.scalajs.dom
import org.scalajs.dom._

/**
	* Entry point for EVMap
	*/
@JSExportTopLevel("EVMap")
object EVMap {

	@JSExport
	def show(parent: dom.html.Div): Unit = {
		println("showing..")
		val view = new EVMap
		JsUtil.replaceAllChildren(parent, view)
		view.update()
	}

	def jsonQuery(url: String, onResponse: (JsValue) => Unit): Unit = {
		println(s"doing jsonQuery to ${url}")
		val req = new XMLHttpRequest
		req.open("GET", url, async = true)
		//req.setRequestHeader("Cache-Control", "no-cache")
		req.responseType = "json"
		req.onreadystatechange = (e: Event) => {
			if (req.readyState == 4 && req.status == 200) {
				val reply = json.readJs(req.response)
				//println(s"RESPONSE: ${json.write2(reply, 2)}")
				onResponse(reply)
			}
		}
		req.setRequestHeader("Content-Type", "application/json")
		req.send()
	}
}

/**
	* The EVMap Div, containing
	*  - the Map itself
	*  - an InfoPanel (only visible when a location was clicked)
	*/
class EVMap extends View with ModalManager with ModalAware {
	attr(`class` := "evmap")

	val map = new Map

	val infoPanel = new InfoPanel
	map.infoPanel = infoPanel

	this += map
	this += infoPanel

	map.locationClickFn = (loc: Model.Location) => {
		infoPanel.update(loc)
	}

	window.onresize = (e: Event) => {
		update()
	}

	init()

	/**
		* Init the page:
		*  1) query the /json data, and then
		*  2) update() the Map
		*/
	def init(): Unit = {
		EVMap.jsonQuery("json", (response)=> {
			val data = Context.jsonLDToScala(response).asInstanceOf[List[Model.Location]]
			map.setLocations(data)
			update()
		})
	}

	/**
		* Update contents, needed
		*  - initially
		*  - after size change
		*/
	def update(): Unit = {
		val w = window.innerWidth
		val h = window.innerHeight - elt.offsetTop
		map.elt.style.width = s"${w}px"
		map.elt.style.height = s"${h}px"
		map.update()
	}
}