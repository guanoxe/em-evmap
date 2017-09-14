package com.koroc.evmap

import com.koroc.json._
import com.koroc.rdf._
import com.koroc.jsonld.JsonLDContext
import Model._

/**
	* The json-ld context, which can translate between
	*  - case classes (from Model._)
	*  - json
	*  - rdf
	*
	* Generated by em-meta code generator.
	*/
object Context extends JsonLDContext {
	//----- Start generated code: context -----
	val contextIri = Iri("http://evmap.koroc.nl/cx")
	val jsContext = JsObject(Map(("evmap", JsString("http://evmap.koroc.nl/s/")), ("xsd", JsString("http://www.w3.org/2001/XMLSchema#")), ("schema", JsString("http://schema.org/")), ("city", JsObject(Map(("@id", JsString("evmap:city")), ("@type", JsString("xsd:string"))))), ("name", JsObject(Map(("@id", JsString("schema:name")), ("@type", JsString("xsd:string"))))), ("location", JsObject(Map(("@id", JsString("evmap:location")), ("@type", JsString("evmap:GeoPoint"))))), ("payWith", JsObject(Map(("@id", JsString("evmap:payWith")), ("@type", JsString("xsd:string"))))), ("description", JsObject(Map(("@id", JsString("evmap:description")), ("@type", JsString("xsd:string"))))), ("icon", JsObject(Map(("@id", JsString("evmap:icon")), ("@type", JsString("evmap:Icon"))))), ("suffix", JsObject(Map(("@id", JsString("evmap:suffix")), ("@type", JsString("xsd:string"))))), ("country", JsObject(Map(("@id", JsString("evmap:country")), ("@type", JsString("xsd:string"))))), ("operator", JsObject(Map(("@id", JsString("evmap:operator")), ("@type", JsString("evmap:Operator"))))), ("status", JsObject(Map(("@id", JsString("evmap:status")), ("@type", JsString("xsd:string"))))), ("address", JsObject(Map(("@id", JsString("evmap:address")), ("@type", JsString("xsd:string"))))), ("lon", JsObject(Map(("@id", JsString("evmap:lon")), ("@type", JsString("xsd:double"))))), ("electricity", JsObject(Map(("@id", JsString("evmap:electricity")), ("@type", JsString("xsd:string"))))), ("roadName", JsObject(Map(("@id", JsString("evmap:roadName")), ("@type", JsString("xsd:string"))))), ("sources", JsObject(Map(("@id", JsString("evmap:sources")), ("@type", JsString("xsd:string"))))), ("lat", JsObject(Map(("@id", JsString("evmap:lat")), ("@type", JsString("xsd:double"))))), ("phone", JsObject(Map(("@id", JsString("evmap:phone")), ("@type", JsString("xsd:string"))))), ("iconFail", JsObject(Map(("@id", JsString("evmap:iconFail")), ("@type", JsString("evmap:Icon"))))), ("accessible", JsObject(Map(("@id", JsString("evmap:accessible")), ("@type", JsString("xsd:string"))))), ("osm", JsObject(Map(("@id", JsString("evmap:osm")), ("@type", JsString("xsd:string")))))))
	val namespaces: Set[RdfNamespace] = Set(evmap, xsd, schema)
	class Pickler {
		val objMap = collection.mutable.Map[Any, JsObject]()
		var newID = 0

		def idOf(x: JsObject): String = {
			x.get("@id") match {
				case None =>
					val id = "_:b" + newID
					newID += 1
					x.underlying = x.underlying.updated("@id", JsString(id))
					id
				case Some(id) =>
					id.str
			}
		}

		def pickle(x: Any, targetType: String = null): JsValue = {
			x match {
				case y: Set[_] =>
					JsArray(y.toList.map(e => pickle(e, targetType)))
				case y: List[_] =>
					JsArray(y.toList.map(e => pickle(e, targetType)))
				case y: String =>
					JsString(y)
				case y: Int =>
					JsNumber(y)
				case y: Double =>
					JsNumber(y)
				case y: Iri =>
					JsObject(Map("@type" `->` JsString("@id"), "@id" `->` JsString(y.str)))
				case y: Icon =>
					pickleIcon(y)
				case y: GeoPoint =>
					pickleGeoPoint(y)
				case y: Operator =>
					pickleOperator(y)
				case y: ChargePoint =>
					pickleChargePoint(y)
				case y: Camping =>
					pickleCamping(y)
			}
		}

		def pickleIcon(x: Icon, targetType: String = null): JsObject = {
			objMap.get(x) match {
				case Some(y) =>
					JsObject(Map("@type" `->` JsString("@id"), "@id" `->` JsString(idOf(y))))
				case None =>
					val l = new collection.mutable.ListBuffer[(String, JsValue)]
					l += "@type" `->` JsString("evmap:Icon")
					if (x.name != null) l += "name" `->` pickle(x.name, "xsd:string")
					if (x.suffix != null) l += "suffix" `->` pickle(x.suffix, "xsd:string")
					val jsObj = JsObject(Map(l: _*))
					objMap.put(x, jsObj)
					jsObj
			}
		}

		def pickleGeoPoint(x: GeoPoint, targetType: String = null): JsObject = {
			objMap.get(x) match {
				case Some(y) =>
					JsObject(Map("@type" `->` JsString("@id"), "@id" `->` JsString(idOf(y))))
				case None =>
					val l = new collection.mutable.ListBuffer[(String, JsValue)]
					l += "@type" `->` JsString("evmap:GeoPoint")
					if (x.lat != 0.0d) l += "lat" `->` pickle(x.lat, "xsd:double")
					if (x.lon != 0.0d) l += "lon" `->` pickle(x.lon, "xsd:double")
					if (x.address != null) l += "address" `->` pickle(x.address, "xsd:string")
					if (x.city != null) l += "city" `->` pickle(x.city, "xsd:string")
					if (x.country != null) l += "country" `->` pickle(x.country, "xsd:string")
					val jsObj = JsObject(Map(l: _*))
					objMap.put(x, jsObj)
					jsObj
			}
		}

		def pickleOperator(x: Operator, targetType: String = null): JsObject = {
			objMap.get(x) match {
				case Some(y) =>
					JsObject(Map("@type" `->` JsString("@id"), "@id" `->` JsString(idOf(y))))
				case None =>
					val l = new collection.mutable.ListBuffer[(String, JsValue)]
					l += "@type" `->` JsString("evmap:Operator")
					if (x.name != null) l += "name" `->` pickle(x.name, "xsd:string")
					if (x.icon != null) l += "icon" `->` pickle(x.icon, "evmap:Icon")
					if (x.iconFail != null) l += "iconFail" `->` pickle(x.iconFail, "evmap:Icon")
					val jsObj = JsObject(Map(l: _*))
					objMap.put(x, jsObj)
					jsObj
			}
		}

		def pickleChargePoint(x: ChargePoint, targetType: String = null): JsObject = {
			objMap.get(x) match {
				case Some(y) =>
					JsObject(Map("@type" `->` JsString("@id"), "@id" `->` JsString(idOf(y))))
				case None =>
					val l = new collection.mutable.ListBuffer[(String, JsValue)]
					l += "@type" `->` JsString("evmap:ChargePoint")
					if (x.name != null) l += "name" `->` pickle(x.name, "xsd:string")
					if (x.description != null) l += "description" `->` pickle(x.description, "xsd:string")
					if (x.location != null) l += "location" `->` pickle(x.location, "evmap:GeoPoint")
					if (x.operator != null) l += "operator" `->` pickle(x.operator, "evmap:Operator")
					if (x.payWith != Nil) l += "payWith" `->` pickle(x.payWith, "xsd:string")
					if (x.osm != Nil) l += "osm" `->` pickle(x.osm, "xsd:string")
					if (x.roadName != null) l += "roadName" `->` pickle(x.roadName, "xsd:string")
					if (x.accessible != null) l += "accessible" `->` pickle(x.accessible, "xsd:string")
					if (x.sources != Nil) l += "sources" `->` pickle(x.sources, "xsd:string")
					if (x.status != null) l += "status" `->` pickle(x.status, "xsd:string")
					val jsObj = JsObject(Map(l: _*))
					objMap.put(x, jsObj)
					jsObj
			}
		}

		def pickleCamping(x: Camping, targetType: String = null): JsObject = {
			objMap.get(x) match {
				case Some(y) =>
					JsObject(Map("@type" `->` JsString("@id"), "@id" `->` JsString(idOf(y))))
				case None =>
					val l = new collection.mutable.ListBuffer[(String, JsValue)]
					l += "@type" `->` JsString("evmap:Camping")
					if (x.name != null) l += "name" `->` pickle(x.name, "xsd:string")
					if (x.description != null) l += "description" `->` pickle(x.description, "xsd:string")
					if (x.electricity != null) l += "electricity" `->` pickle(x.electricity, "xsd:string")
					if (x.phone != null) l += "phone" `->` pickle(x.phone, "xsd:string")
					if (x.location != null) l += "location" `->` pickle(x.location, "evmap:GeoPoint")
					if (x.osm != Nil) l += "osm" `->` pickle(x.osm, "xsd:string")
					val jsObj = JsObject(Map(l: _*))
					objMap.put(x, jsObj)
					jsObj
			}
		}
	}
	class Unpickler {
		val refMap = collection.mutable.Map[String, Any]()

		def unpickle(x: JsValue, rdfType: String = null, listType: String = null): Any = {
			x match {
				case y: JsObject =>
					unpickle_NodeObject(y, rdfType)
				case y: JsString =>
					y.str
				case y: JsNumber =>
					rdfType match {
						case null =>
							y.toBigDecimal
						case "xsd:int" =>
							y.toInt
						case "xsd:long" =>
							y.toLong
						case "xsd:double" =>
							y.toDouble
						case "xsd:float" =>
							y.toFloat
					}
				case y: JsBoolean =>
					y.toBoolean
				case JsNull =>
					null
				case y: JsArray =>
					listType match {
						case "Set" =>
							y.toList.map(e => unpickle(e, rdfType, null)).toSet
						case _ =>
							y.toList.map(e => unpickle(e, rdfType, null))
					}
			}
		}

		def unpickle_NodeObject(x: JsObject, rdfType: String = null): Any = {
			val t: String = x.toMap.get("@type").map(_.str).getOrElse(rdfType)
			t match {
				case "@id" =>
					refMap(x.get("@id").get.str)
				case _ =>
					val obj = t match {
						case "evmap:Icon" =>
							unpickleIcon(x)
						case "evmap:GeoPoint" =>
							unpickleGeoPoint(x)
						case "evmap:Operator" =>
							unpickleOperator(x)
						case "evmap:ChargePoint" =>
							unpickleChargePoint(x)
						case "evmap:Camping" =>
							unpickleCamping(x)
					}
					x.get("@id") match {
						case Some(id) =>
							refMap.put(id.str, obj)
						case None =>
					}
					obj
			}
		}

		def unpickleIcon(x: JsObject): Icon = {
			Icon(name = x.toMap.get("name").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, suffix = x.toMap.get("suffix").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull)
		}

		def unpickleGeoPoint(x: JsObject): GeoPoint = {
			GeoPoint(lat = x.toMap.get("lat").map(v => unpickle(v, "xsd:double").asInstanceOf[Double]).getOrElse(0.0d), lon = x.toMap.get("lon").map(v => unpickle(v, "xsd:double").asInstanceOf[Double]).getOrElse(0.0d), address = x.toMap.get("address").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, city = x.toMap.get("city").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, country = x.toMap.get("country").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull)
		}

		def unpickleOperator(x: JsObject): Operator = {
			Operator(name = x.toMap.get("name").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, icon = x.toMap.get("icon").map(v => unpickle(v, "evmap:Icon").asInstanceOf[Icon]).orNull, iconFail = x.toMap.get("iconFail").map(v => unpickle(v, "evmap:Icon").asInstanceOf[Icon]).orNull)
		}

		def unpickleChargePoint(x: JsObject): ChargePoint = {
			ChargePoint(name = x.toMap.get("name").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, description = x.toMap.get("description").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, location = x.toMap.get("location").map(v => unpickle(v, "evmap:GeoPoint").asInstanceOf[GeoPoint]).orNull, operator = x.toMap.get("operator").map(v => unpickle(v, "evmap:Operator").asInstanceOf[Operator]).orNull, payWith = x.toMap.get("payWith").map(v => unpickle(v, "xsd:string").asInstanceOf[List[String]]).getOrElse(Nil), osm = x.toMap.get("osm").map(v => unpickle(v, "xsd:string").asInstanceOf[List[String]]).getOrElse(Nil), roadName = x.toMap.get("roadName").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, accessible = x.toMap.get("accessible").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, sources = x.toMap.get("sources").map(v => unpickle(v, "xsd:string").asInstanceOf[List[String]]).getOrElse(Nil), status = x.toMap.get("status").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull)
		}

		def unpickleCamping(x: JsObject): Camping = {
			Camping(name = x.toMap.get("name").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, description = x.toMap.get("description").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, electricity = x.toMap.get("electricity").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, phone = x.toMap.get("phone").map(v => unpickle(v, "xsd:string").asInstanceOf[String]).orNull, location = x.toMap.get("location").map(v => unpickle(v, "evmap:GeoPoint").asInstanceOf[GeoPoint]).orNull, osm = x.toMap.get("osm").map(v => unpickle(v, "xsd:string").asInstanceOf[List[String]]).getOrElse(Nil))
		}
	}

	def scalaToJsonLD(x: Any): JsObject = {
		new Pickler().pickle(x) match {
			case y: JsObject =>
				y.updated("@context", JsString(contextIri.str))
			case y =>
				JsObject(Map("@context" `->` JsString(contextIri.str), "@graph" `->` y))
		}
	}

	def jsonLDToScala(x: JsValue): Any = {
		x match {
			case y: JsObject =>
				y.get("@context") match {
					case Some(cx) =>
					case None =>
				}
				y.get("@graph") match {
					case Some(g) =>
						new Unpickler().unpickle(g)
					case None =>
						new Unpickler().unpickle_NodeObject(y)
				}
			case _ =>
				new Unpickler().unpickle(x)
		}
	}

	//----- End generated code -----

	def lookupName(s: String): Iri = {
		throw new Exception(s"Name not found: ${s}")
	}


	val automaticTransfer = null // boolean
	val balance = null
	val address = schema.address

	val PostalAddress = schema.PostalAddress
	val addressCountry = schema.addressCountry
	val addressLocality = schema.addressLocality
	val postalCode = schema.postalCode
	val streetAddress = schema.streetAddress
	val streetAddress_street = sbw.`streetAddress-street`
	val streetAddress_nr = sbw.`streetAddress-nr`
	val streetAddress_ext = sbw.`streetAddress-ext`


}

