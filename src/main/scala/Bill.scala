import menuitems.{Drink, Food, Hot, MenuItem}
import java.time.LocalTime

case class Bill(items: List[MenuItem], loyalty: Loyalty = Loyalty(), currency: String = "GBP", time: LocalTime = LocalTime.now()) {

  private val itemsLength = items.length

  def calculatePreSCBill: BigDecimal = time.getHour match {
    case x if x >= 18 && x <= 21 => items.map(item =>
      if (item.foodType == Drink) item.cost / 2
      else item.cost).sum
    case _ => items.map(_.cost).sum
  }

  def calculateServiceCharge: BigDecimal = {
//    if (items.map(item => item.foodType).contains(Food)) {
//      if (items.filter(_.foodType == Food).map(item => item.temperature).contains(Hot)) {
//        val hotFoodSC = calculatePreSCTotal * 0.2
//        if (hotFoodSC > 20) 20
//        else hotFoodSC
//      } else calculatePreSCTotal * 0.1
//    } else 0
    val unrounded: BigDecimal = items.count(item => item.foodType == Drink) match {
      case this.itemsLength => 0
      case _ => items.count(item => item.temperature == Hot && item.foodType == Food) match {
        case 0 => calculatePreSCBill * 0.1
        case _ => items.count(item => item.temperature == Hot && item.foodType == Food && item.premium) match {
          case 0 => BigDecimal(20) min calculatePreSCBill * 0.2
          case _ => BigDecimal(40) min calculatePreSCBill * 0.25
        }
      }
    }
    unrounded.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)
  }

  def calculateLoyaltyDiscount: BigDecimal = items.filter{_.premium == false}.map{_.cost}.sum * loyalty.discount

  def calculateBill: BigDecimal = Currencies.exchangeRates(currency) * (calculatePreSCBill - calculateLoyaltyDiscount + calculateServiceCharge)
}
