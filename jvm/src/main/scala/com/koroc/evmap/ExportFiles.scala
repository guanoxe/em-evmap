package com.koroc.evmap

import java.nio.file.Path

import com.koroc.io._

import scala.collection.immutable.Seq
import scala.sys.process._

/**
	* Manages the generation of the static export files:
	*  - kml, kmz
	*  - icons
	*/
object ExportFiles {
	var downloadsDirty = true

	/**
		* produce/update export files if needed.
		*/
	def make(webDir: Path, data: List[Model.Location]): Unit = {
		if (!downloadsDirty) return

		IOUtil.writeText(KMLFormat.toKML(data), webDir / "img" / "doc.kml")
		Process(Seq("zip", "-r", "../map1.kmz", "doc.kml", "map-icons-sander"), (webDir / "img").toFile).!
		Process(Seq("zip", "-r", "../map-icons-sander.zip", "map-icons-sander"), (webDir / "img").toFile).!
		Process(Seq("rm", "doc.kml"), (webDir / "img").toFile).!

		downloadsDirty = false
	}


}
