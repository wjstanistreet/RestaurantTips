import menuitems.{Beer, MenuItem, Ramen, Sake, Theme, Wagyu, Yakitori}
import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers
class ThemeSpec extends AnyFlatSpec with Matchers{
  "A restaurant theme" should "contain different items if Japanese is chosen" in {
    val theme: Set[MenuItem] = Theme.cuisine("Japanese")

    theme.contains(Sake) should be (true)
    theme.contains(Beer) should be (true)
    theme.contains(Ramen) should be (true)
    theme.contains(Yakitori) should be (true)
    theme.contains(Wagyu) should be (true)
  }

  it should "contain different items if Italian is chosen" in {
    val theme: Set[MenuItem] = Theme.cuisine("Italian")

    theme.size should be (5)
  }
}
