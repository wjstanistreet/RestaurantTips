case class Bill(items: List[MenuItem]) {

  private val itemsLength = items.length

  def calculatePreSCTotal: BigDecimal = {
    items.map{_.cost}.sum
  }

  def calculateServiceCharge: BigDecimal = {
//    if (items.map(item => item.foodType).contains(Food)) {
//      if (items.filter(_.foodType == Food).map(item => item.temperature).contains(Hot)) {
//        val hotFoodSC = calculatePreSCTotal * 0.2
//        if (hotFoodSC > 20) 20
//        else hotFoodSC
//      } else calculatePreSCTotal * 0.1
//    } else 0

    items.count(item => item.foodType == Drink) match {
      case this.itemsLength => 0
      case _ => items.count(item => item.temperature == Hot && item.foodType == Food) match {
        case 0 => calculatePreSCTotal * 0.1
        case _ => BigDecimal(20) min calculatePreSCTotal * 0.2
      }
    }
  }
}
