package com.koroc.evmap

import akka.http.scaladsl.model.Uri
import com.koroc.backend._
import com.koroc.html._
import com.koroc.html.Attrs._

object Page {
	def apply(content: Div, cssDeps: List[Uri] = Nil, jsDeps: Seq[Uri] = Nil)(implicit wx: WebRequestContext): HTML = {
		val cssDeps2 = wx.css.main :: cssDeps

		val heads: List[HTMLElement] =
			Title("EV map") ::
			Link(rel := "shortcut icon", `type` := "image/png", href := wx.img.favicon) ::
			cssDeps2.map(uri => Link(rel := "stylesheet", `type` := "text/css", href := uri.toString)) ++
			jsDeps.map(uri => Script(src := uri))

		HTML(
			Head(heads:_*),
			Body(
				PageHeader(),
				Div(id := "page-content", `class` := "page-content", content),
				PageFooter()
			)
		)
	}

}

object PageHeader {
	def apply()(implicit rx: WebRequestContext) =
		Div(id := "page-header",
			HeaderTopStrip()
		)
}

object HeaderTopStrip {
	def apply()(implicit wx: WebRequestContext) =
		Div(
			`class` := "header-top-strip indented-container",
			Span(`class` := "logo-title", "EV map"),
		)
}

object PageFooter {
	def apply() =
		Div(id := "page-footer")
}

