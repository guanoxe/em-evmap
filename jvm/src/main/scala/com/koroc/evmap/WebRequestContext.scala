package com.koroc.evmap

import java.nio.file.Path

import akka.http.scaladsl.model.Uri
import com.koroc.backend._
import com.koroc.io._

/**
	* Context information needed for responding to http requests.
	* Here only img/css/js paths are needed, but WebRequestContext in other
	* apps may include session info.
	*/
case class WebRequestContext(baseUri: Uri, path: Uri.Path, webDir: Path) {
	def loggedIn = false

	lazy val imgDir: Path = webDir / "img"
	lazy val cssDir = webDir / "css"
	lazy val jsDir = webDir / "js"

	lazy val imgUri: Uri = baseUri / "img"
	lazy val cssUri = baseUri / "css"
	lazy val jsUri = baseUri / "js"

	object css {
		def apply(name: String): Uri = cssUri / name
		lazy val ol = cssUri / "ol.css"
		lazy val main = cssUri / "main.css"
		lazy val evmap = cssUri / "evmap.css"
	}

	object js {
		def apply(name: String): Uri = jsUri / name
		lazy val evmap = jsUri / "evmap.js"
		lazy val ol = jsUri / "ol.js"
	}

	object img {
		def apply(name: String): Uri = imgUri / name
		lazy val favicon = imgUri / "favicon.png"
	}


}

