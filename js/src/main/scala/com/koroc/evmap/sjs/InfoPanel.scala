package com.koroc.evmap.sjs

import com.koroc.evmap.Model
import com.koroc.evmap.Model.{Camping, ChargePoint}
import com.koroc.sjs.html.Attr.{`class`, href, src}
import com.koroc.sjs.html.{A, Div, Img, P, Span}

/**
	* Div showing info about a selected location.
	* May or may not be visible.
	*/
class InfoPanel extends Div {
		attr(`class` := "info-panel")
		def update(loc: Model.Location): Unit = {
			removeAllChildren()
			this += P(`class` := "cp-info-title", loc.name)
			loc match {
				case cp: ChargePoint =>
					this += InfoRow("road", cp.roadName)
					this += InfoRow("accessible-from", cp.accessible)
					this += InfoRow("status", cp.status)
					this += InfoRow("info from", cp.sources.mkString(","))
					this += InfoRow("description", cp.description)
					this += InfoRow("operator", cp.operator.name)
					this += InfoRow("payment", cp.payWith.mkString(","))

				case c: Camping =>
					this += InfoRow("description", c.description)
					this += InfoRow("electricity", c.electricity)
					this += InfoRow("address", c.location.address)
					this += InfoRow("phone", c.phone)

				case _ =>
					this += InfoRow("description", loc.description)
			}
			this += A(href := s"https://www.google.com/maps/search/?api=1&query=${loc.location.lat},${loc.location.lon}", Img(src := "img/google-maps.svg"))
		}
}


object InfoRow {
	def apply(key: String, value: String): P = {
		if (value == null) null else P(`class` := "cp-info-row", Span(`class` := "key", key + ": "), Span(`class` := "value", value))
	}
}
