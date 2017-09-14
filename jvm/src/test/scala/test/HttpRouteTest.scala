package test

import akka.http.scaladsl.model.{ContentType, MediaTypes, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.koroc.evmap.{Main, StaticData}
import org.scalatest.{FlatSpec, Matchers}

class HttpRouteTest extends FlatSpec with Matchers with ScalatestRouteTest {

	behavior of "http route"

	Main.init()

	it should "have a / page" in {
		Get() ~> Main.route ~> check {
			status shouldEqual StatusCodes.OK
		}
	}

	it should "have a /map page" in {
		Get("/map") ~> Main.route ~> check {
			status shouldEqual StatusCodes.OK
		}
	}

	it should "serve a favicon" in {
		Get("/favicon.ico") ~> Main.route ~> check {
			status shouldEqual StatusCodes.OK
		}
	}

	it should "serve css" in {
		Get("/css/evmap.css") ~> Main.route ~> check {
			status shouldEqual StatusCodes.OK
		}
	}

	it should "remove trailing slashes" in {
		Get("/map/") ~> Main.route ~> check {
			status shouldEqual StatusCodes.MovedPermanently
		}
	}

	it should "serve /json" in {
		Get("/json") ~> Main.route ~> check {
			status shouldEqual StatusCodes.OK
		}
	}

	it should "serve the evmap.js frontend code" in {
		Get("/js/evmap.js") ~> Main.route ~> check {
			status shouldEqual StatusCodes.OK
		}
	}

	it should "serve /kml" in {
		Get("/kml") ~> Main.route ~> check {
			status shouldEqual StatusCodes.OK
		}
	}

	it should "serve /icons" in {
		Get("/icons") ~> Main.route ~> check {
			status shouldEqual StatusCodes.OK
			contentType shouldEqual ContentType(MediaTypes.`application/octet-stream`)
		}
	}

	it should "serve /kmz" in {
		Get("/kmz") ~> Main.route ~> check {
			status shouldEqual StatusCodes.OK
			contentType shouldEqual ContentType(MediaTypes.`application/vnd.google-earth.kmz`)
		}
	}

}