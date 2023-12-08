import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers
class BillSpec extends AnyFlatSpec with Matchers {
  "a bill" should "have a list of items" in {
    val order: List[MenuItem] = List(Cola)
    val bill: Bill = Bill(order)

    bill.items.length should be(1)
  }

  "calculating a bill's total" should "return a sum of the items: 1 item/1 type" in {
      val order: List[MenuItem] = List(Cola)
      val bill: Bill = Bill(order)

      bill.calculatePreSCBill should be(BigDecimal(0.5))
  }

  it should "return a sum of the items: 2 items/1 type" in {
    val order: List[MenuItem] = List(Cola, Cola)
    val bill: Bill = Bill(order)

    bill.calculatePreSCBill should be(BigDecimal(1.0))
  }

  it should "return a sum of the items: 2 items/2 types" in {
    val order: List[MenuItem] = List(Cola, Coffee)
    val bill: Bill = Bill(order)

    bill.calculatePreSCBill should be(BigDecimal(1.50))
  }

  it should "return a sum of the items: many items/many types" in {
    val order: List[MenuItem] = List(Cola, Coffee, CheeseSandwich, SteakSandwich)
    val bill: Bill = Bill(order)

    bill.calculatePreSCBill should be(BigDecimal(8.0))
  }

  "service charge" should "be 0 if all items are drinks" in {
    val order: List[MenuItem] = List(Cola, Coffee)
    val bill: Bill = Bill(order)

    bill.calculateServiceCharge should be(BigDecimal(0))
  }

  it should "be 10% if food is included" in {
    val order: List[MenuItem] = List(Cola, Coffee, CheeseSandwich)
    val bill: Bill = Bill(order)

    bill.calculateServiceCharge should be(BigDecimal(0.35))
  }

  it should "be 20% if hot food is included" in {
    val order: List[MenuItem] = List(Cola, Coffee, SteakSandwich)
    val bill: Bill = Bill(order)

    bill.calculateServiceCharge should be(BigDecimal(1.2))
  }

  it should "be 20% if hot food is included up to a max of £20" in {
    val order: List[MenuItem] = List.fill(25)(SteakSandwich)
    val bill: Bill = Bill(order)

    bill.calculateServiceCharge should be(BigDecimal(20))
  }

  it should "be 25% if a premium item is included" in {
    val order: List[MenuItem] = List(Lobster, Lobster, CheeseSandwich)
    val bill: Bill = Bill(order)

    bill.calculateServiceCharge should be(BigDecimal(13))
  }

  it should "be 25% if a premium item is included up to a max of £40" in {
    val order: List[MenuItem] = List.fill(15)(Lobster)
    val bill: Bill = Bill(order)

    bill.calculateServiceCharge should be(BigDecimal(40))
  }

  "A customer with zero loyalty stars" should "receive no discount (pre-service charge)" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(1))

    bill.calculateLoyaltyDiscount should be(BigDecimal(0))
  }

  "A customer with one loyalty star" should "receive no discount (pre-service charge)" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(1))

    bill.calculateLoyaltyDiscount should be(BigDecimal(0))
  }

  "A customer with 2 loyalty stars" should "receive no discount (pre-service charge)" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(2))

    bill.calculateLoyaltyDiscount should be(BigDecimal(0))
  }

  "A customer with 3 loyalty stars" should "receive a 7.5% discount (pre-service charge)" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(3))

    bill.calculateLoyaltyDiscount should be(BigDecimal(1.5))
  }

  "A customer with 4 loyalty stars" should "receive a 10% discount (pre-service charge)" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(4))

    bill.calculateLoyaltyDiscount should be(BigDecimal(2))
  }

  "A customer with 8 loyalty stars" should "receive a 20% discount (pre-service charge)" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(8))

    bill.calculateLoyaltyDiscount should be(BigDecimal(4))
  }

  "A customer with 10 loyalty stars" should "receive a 20% discount (pre-service charge)" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(10))

    bill.calculateLoyaltyDiscount should be(BigDecimal(4))
  }

  "A customer with 12 loyalty stars" should "receive a 20% discount (pre-service charge)" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(12))

    bill.calculateLoyaltyDiscount should be(BigDecimal(4))
  }

  "A customer with 8 loyalty stars" should "receive a 20% discount (pre-service charge) NOT including premium items" in {
    val order: List[MenuItem] = List(Lobster, SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(8))

    bill.calculatePreSCBill should be (45)
    bill.calculateLoyaltyDiscount should be(BigDecimal(4))
  }

  "A customer with 8 loyalty stars" should "receive a 0% discount for a list of ONLY premium items" in {
    val order: List[MenuItem] = List(Lobster, Lobster, Lobster)
    val bill: Bill = Bill(order, Loyalty(8))

    bill.calculateLoyaltyDiscount should be(BigDecimal(0))
  }
}
