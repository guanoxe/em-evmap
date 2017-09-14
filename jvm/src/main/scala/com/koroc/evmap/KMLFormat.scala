package com.koroc.evmap

import scala.collection.mutable.ListBuffer
import Model._

/**
	* Produce .kml format, which can be used to import
	* locations into Locus Maps, Google Maps, etc.
	*/
object KMLFormat {
	def toKML(locations: List[Model.Location]): String = {

		def descr(loc: Model.Location): scala.xml.PCData = {
			val l = new ListBuffer[String]

			def add(key: String, value: String): Unit = {
				if (value != null)
					l += s"${key}: ${value}"
			}

			def addn(key: String, value: List[String]): Unit = {
				if (value != Nil) {
					//if (l.nonEmpty) l += <br/>
					l += s"""${key}: ${value.mkString(",")}"""
				}
			}

			loc match {
				case cp: ChargePoint =>
					add("road", cp.roadName)
					add("accessible-from", cp.accessible)
					add("status", cp.status)
					addn("info from", cp.sources)
					add("description", cp.description)
					if (cp.operator != null) add("operator", cp.operator.name)
					addn("payment", cp.payWith)
				case c: Camping =>
					add("description", loc.description)
					add("electricity", c.electricity)
					add("address", c.location.address)
					add("phone", c.phone)

				case _ =>
					add("description", loc.description)
			}
			//<description>{l}</description>
			scala.xml.PCData(l.mkString("\n"))
		}

		val kml =
			<kml xmlns="http://www.opengis.net/kml/2.2">
				<Document>
					{locations.map(_.icon.name).toSet.map { iconName: String =>
					<Style id={iconName}>
						<IconStyle>
							<Icon>
								<href>map-icons-sander/
									{iconName}
									.png</href>
							</Icon>
							<hotSpot x="0.5" y="0.0" xunits="fraction" yunits="fraction"></hotSpot>
						</IconStyle>
					</Style>
				}}{locations.map { l =>
					<PlaceMark>
						<name>
							{l.name}
						</name>
						<description>
							{descr(l)}
						</description>
						<styleUrl>#
							{l.icon.name}
						</styleUrl>
						<Point>
							<coordinates>
								{l.location.lon}
								,
								{l.location.lat}
							</coordinates>
						</Point>
					</PlaceMark>

				}}
				</Document>
			</kml>

		//val pp = new PrettyPrinter(500, 2)
		//"""<?xml version="1.0" encoding="UTF-8"?>""" + "\n" + pp.format(kml)
		"""<?xml version="1.0" encoding="UTF-8"?>""" + "\n" + kml.toString
	}
}
