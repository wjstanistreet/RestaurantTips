import menuitems.{CheeseSandwich, Coffee, Cola, SteakSandwich}
import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers

class MenuSpec extends AnyFlatSpec with Matchers {

  "A menu" should "have a collection of menu items" in {
    val menu = Menu()

    val itemList = Set(Cola, Coffee, CheeseSandwich, SteakSandwich)

    menu.items should be (itemList)
  }
}
