import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers
class BillSpec extends AnyFlatSpec with Matchers {
  "a bill" should "have a list of items" in {
    val order: List[MenuItem] = List(Cola)
    val bill: Bill = Bill(order)

    bill.items.length should be(1)
  }

  "calculating a bill's total" should
    "return a sum of the items: 1 item/1 type" in {
      val order: List[MenuItem] = List(Cola)
      val bill: Bill = Bill(order)

      bill.calculateTotal should be(BigDecimal(0.5))
  }

  it should "return a sum of the items: 2 items/1 type" in {
    val order: List[MenuItem] = List(Cola, Cola)
    val bill: Bill = Bill(order)

    bill.calculateTotal should be(BigDecimal(1.0))
  }

  it should "return a sum of the items: 2 items/2 types" in {
    val order: List[MenuItem] = List(Cola, Coffee)
    val bill: Bill = Bill(order)

    bill.calculateTotal should be(BigDecimal(1.50))
  }

  it should "return a sum of the items: many items/many types" in {
    val order: List[MenuItem] = List(Cola, Coffee, CheeseSandwich, SteakSandwich)
    val bill: Bill = Bill(order)

    bill.calculateTotal should be(BigDecimal(8.0))
  }
}
