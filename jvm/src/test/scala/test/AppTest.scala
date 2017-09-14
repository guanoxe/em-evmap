package test

import com.koroc.evmap.{Main, StaticData}
import org.scalatest.{FlatSpec, Matchers}

class AppTest extends FlatSpec with Matchers {

	behavior of "app"

	Main.init()

	it should "have static data" in {
		StaticData.locations.isEmpty shouldBe false
	}


}
