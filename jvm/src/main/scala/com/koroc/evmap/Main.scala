package com.koroc.evmap

import java.nio.file.{Files, Path, Paths}

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes.MovedPermanently
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.ContentDispositionTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.koroc.backend.Marshallers._
import com.koroc.backend._
import com.koroc.html.Attrs._
import com.koroc.html._
import com.koroc.io._
import com.koroc.json
import com.koroc.rdf.io.NTriples

import scala.collection.immutable.Seq

object Main {
	implicit var system: ActorSystem = _

	case class Config(baseUri: String = null, webDir: Path = null, bindHost: String = null, bindPort: Int = 0)
	var config: Config = _

	def main(args: Array[String]): Unit = {
		if (args.length != 0) {
			System.err.println(s"Usage: em-evmap [ -Dconfig.file=<my.conf> ]")
			System.exit(1)
		}

		init()

		implicit val materializer = ActorMaterializer()
		implicit val executionContext = system.dispatcher
		val bindingFuture = Http().bindAndHandle(route, config.bindHost, config.bindPort)
	}

	def init(): Unit = {
		system = ActorSystem()
		config = readConfig(system)
	}

	def readConfig(system: ActorSystem): Config = {
		val conf = system.settings.config.getConfig("em-evmap")
		Config(
			bindHost = conf.getString("bind-host"),
			bindPort = conf.getInt("bind-port"),
			baseUri = conf.getString("base-uri"),
			webDir = Paths.get(conf.getString("web-dir"))
		)
	}

	/**
		* The list of locations to be shown, either
		*  - map1.json file, if exists
		*  - Data.locations
		*/
	def data(wx: WebRequestContext): List[Model.Location] = {
		if (Files.exists(wx.webDir / "map1.json")) {
			val jsonData = json.read(IOUtil.readText(wx.webDir / "map1.json"))
			Context.jsonLDToScala(jsonData).asInstanceOf[List[Model.Location]]
		} else
			StaticData.locations
	}

	lazy val route: Route =
		extractRequest { httpRequest =>
			provide(WebRequestContext(baseUri = config.baseUri, path = httpRequest.uri.path, webDir = config.webDir)) {
				webRoute
			}
		}

	/**
		* A WebRoute is like a Route, but 'wraps' it with extra info like webDir, session, etc.
		*/
	type WebRoute = (WebRequestContext) => Route

	lazy val webRoute: WebRoute = { wx =>
		implicit val wx1 = wx

		pathSingleSlash {
			complete(
				// the root (main) page
				Page(
					cssDeps = List(wx.css.evmap),
					content =
						Div(
							Ul(
								Li(
									A(href := wx.baseUri / "map", "EVMap - Snelladers Frankrijk 2017"),
									Span(
										`class` := "download-link",
										"(download ",
										A(href := wx.baseUri / "icons", " icons "),
										"or",
										A(href := wx.baseUri / "kmz", " kmz "),
										")"
									)
								)
							)
						)
				)(wx)
			)
		} ~
		redirectToNoTrailingSlashIfPresent(MovedPermanently) {
			path("favicon.ico") {
				getFromFile(wx.imgDir / "favicon.png")
			} ~
			pathPrefix("img") {
				getFromDirectory(wx.imgDir.toAbsolutePath.toString)
			} ~
			pathPrefix("css") {
				getFromDirectory(wx.cssDir.toAbsolutePath.toString)
			} ~
			pathPrefix("js") {
				getFromDirectory(wx.jsDir.toAbsolutePath.toString)
			} ~
			path("map") {
				complete(
					// the /map page, showing the dynamic evmap.sjs.EVMap scala-js
					Page(
						cssDeps = List(wx.css.ol, wx.css.evmap),
						jsDeps = List(wx.js.evmap, wx.js.ol),
						content =
							Div(
								Script(""" EVMap.show(document.getElementById('page-content')); """)
							)
					)(wx)
				)
			} ~
			path("kml") {
				val header1: HttpHeader = headers.`Content-Disposition`(ContentDispositionTypes.attachment, Map("filename" -> "map1.kml"))
				complete(HttpResponse(entity = HttpEntity(KMLFormat.toKML(data(wx))), headers = Seq(header1)))
			} ~
			path("kmz") {
				ExportFiles.make(wx.webDir, data(wx))
				val header1: HttpHeader = headers.`Content-Disposition`(ContentDispositionTypes.attachment, Map("filename" -> "map1.kmz"))
				respondWithHeader(header1) {
					getFromFile((wx.webDir / "map1.kmz").toFile, ContentType(MediaTypes.`application/vnd.google-earth.kmz`))
				}
			} ~
			path("icons") {
				ExportFiles.make(wx.webDir, data(wx))
				val header1: HttpHeader = headers.`Content-Disposition`(ContentDispositionTypes.attachment, Map("filename" -> "map-icons-sander.zip"))
				respondWithHeader(header1) {
					getFromFile((wx.webDir / "map-icons-sander.zip").toFile, ContentType(MediaTypes.`application/octet-stream`))
				}
			} ~
			path("json") {
				complete(Context.scalaToJsonLD(data(wx)))
			}
		}
	}


}