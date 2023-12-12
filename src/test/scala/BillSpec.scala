import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers
class BillSpec extends AnyFlatSpec with Matchers {
  "A bill" should "have a list of items" in {
    val order: List[MenuItem] = List(Cola)
    val bill: Bill = Bill(order)

    bill.items.length should be(1)
  }

  "Calculating a bill's total" should "return a sum of the items: 1 item/1 type" in {
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

  "Service charge" should "be 0 if all items are drinks" in {
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

  "A customer" should "receive no discount (pre-service charge) with zero loyalty stars " in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(1))

    bill.calculateLoyaltyDiscount should be(BigDecimal(0))
  }

  it should "receive no discount (pre-service charge) with one loyalty star" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(1))

    bill.calculateLoyaltyDiscount should be(BigDecimal(0))
  }

  it should "receive no discount (pre-service charge) with 2 loyalty stars" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(2))

    bill.calculateLoyaltyDiscount should be(BigDecimal(0))
  }

  it should "receive a 7.5% discount (pre-service charge) with 3 loyalty stars" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(3))

    bill.calculateLoyaltyDiscount should be(BigDecimal(1.5))
  }

  it should "receive a 10% discount (pre-service charge) with 4 loyalty stars" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(4))

    bill.calculateLoyaltyDiscount should be(BigDecimal(2))
  }

  it should "receive a 20% discount (pre-service charge) with 8 loyalty stars" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(8))

    bill.calculateLoyaltyDiscount should be(BigDecimal(4))
  }

  it should "receive a 20% discount (pre-service charge) with 10 loyalty stars" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(10))

    bill.calculateLoyaltyDiscount should be(BigDecimal(4))
  }

  it should "receive a 20% discount (pre-service charge) with 12 loyalty stars" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(12))

    bill.calculateLoyaltyDiscount should be(BigDecimal(4))
  }

  it should "receive a 20% discount (pre-service charge) NOT including premium items with 8 loyalty stars" in {
    val order: List[MenuItem] = List(Lobster, SteakSandwich, SteakSandwich, Coffee, SteakSandwich, SteakSandwich, Coffee)
    val bill: Bill = Bill(order, Loyalty(8))

    bill.calculatePreSCBill should be (45)
    bill.calculateLoyaltyDiscount should be(BigDecimal(4))
  }

  it should "receive a 0% discount for a list of ONLY premium items with 8 loyalty stars" in {
    val order: List[MenuItem] = List(Lobster, Lobster, Lobster)
    val bill: Bill = Bill(order, Loyalty(8))

    bill.calculateLoyaltyDiscount should be(BigDecimal(0))
  }

  "Calculating a bill" should "return the full amount: premium food - no discount" in {
    val order: List[MenuItem] = List(Lobster, Lobster, Coffee, Cola, Cola)
    val bill: Bill = Bill(order, Loyalty(2))

    bill.calculateBill should be(65)
  }

  it should "return the full amount: premium food - 10% discount" in {
    val order: List[MenuItem] = List(Lobster, Lobster, Coffee, Cola, Cola)
    val bill: Bill = Bill(order, Loyalty(4))

    bill.calculateBill should be(64.8)
  }

  it should "return the full amount: hot food - 20% discount" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, Coffee, CheeseSandwich)
    val bill: Bill = Bill(order, Loyalty(8))

    bill.calculateBill should be(BigDecimal(13))
  }

  "A specified currency" should "change the value of bill by 1.26x if USD" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, Coffee, CheeseSandwich, CheeseSandwich)
    val bill: Bill = Bill(order, Loyalty(2), "USD")

    bill.calculateBill should be(BigDecimal(22.68))
  }

  it should "change the value of bill by 1.16x if EUR" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, Coffee, CheeseSandwich, CheeseSandwich)
    val bill: Bill = Bill(order, Loyalty(2), "EUR")

    bill.calculateBill should be(BigDecimal(20.88))
  }

  it should "change the value of bill by 182.71x if JPY" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, Coffee, CheeseSandwich, CheeseSandwich)
    val bill: Bill = Bill(order, Loyalty(2), "JPY")

    bill.calculateBill should be(BigDecimal(3288.78))
  }

  it should "change the value of bill by 9.02x if CNY" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, Coffee, CheeseSandwich, CheeseSandwich)
    val bill: Bill = Bill(order, Loyalty(2), "CNY")

    bill.calculateBill should be(BigDecimal(162.36))
  }

  it should "change the value of bill by 1.69x if SGD" in {
    val order: List[MenuItem] = List(SteakSandwich, SteakSandwich, Coffee, Coffee, CheeseSandwich, CheeseSandwich)
    val bill: Bill = Bill(order, Loyalty(2), "SGD")

    bill.calculateBill should be(BigDecimal(30.42))
  }
}
