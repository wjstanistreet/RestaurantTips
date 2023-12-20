import menuitems.{Drink, Food, Hot, MenuItem}
import java.time.LocalTime

case class Bill(
                 items: List[MenuItem],
                 loyalty: Loyalty = Loyalty(),
                 currency: String = "GBP",
                 time: LocalTime = LocalTime.now(),
                 theme: String = "Regular",
                 server: String = "John Smith") {

  private val itemsLength: Int = items.length
  val isHappyHour: Boolean = time.getHour >= 18 && time.getHour <= 21

  def calculatePreSCBill: BigDecimal =
    if (isHappyHour) happyHourTotal
    else itemSum

  def itemSum: BigDecimal = items.map(_.cost).sum

  def happyHourTotal: BigDecimal = items.map(item =>
      if (item.foodType == Drink) item.cost / 2
      else item.cost).sum

  def calculateServiceCharge: BigDecimal = {
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

  def calculateTip: BigDecimal = 0.1 * calculateBill
}
