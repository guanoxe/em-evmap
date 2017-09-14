package com.koroc.evmap

/**
	* The domain model, as case classes.
	*/
object Model {
	case class Icon(name: String, suffix: String)

	// a meaningful location on a map
	trait Location {
		def name: String
		def description: String
		def location: GeoPoint
		def icon: Icon
	}

	// just a point on a map
	case class GeoPoint(lat: Double = 0.0, lon: Double = 0.0, address: String = null, city: String = null, country: String = null)

	// an operator of ev chargers
	case class Operator(
		id: String = null,
		name: String,
		icon: Icon = null,
		iconFail: Icon = null
	)

	// a charge point is a location with charge-point specific info
	case class ChargePoint(
		name: String = null,
		description: String = null,
		location: GeoPoint,
		operator: Operator = null,
		payWith: List[String] = Nil,
		osm: List[String] = Nil,
		roadName: String = null,
		accessible: String = null,
		sources: List[String] = Nil,
		status: String = "active"
	) extends Location {
		def icon: Icon = {
			if (status == "active")
				operator.icon
			else if (operator.iconFail != null) operator.iconFail else operator.icon
		}
	}

	// a camping is a location with camping-specific info
	case class Camping(
		name: String,
		description: String = null,
		location: GeoPoint,
		osm: List[String] = Nil,
		electricity: String = null,
		phone: String = null
	) extends Location {
		def icon: Icon = Icon("camping","svg")
	}

}
