case class Bill(items: List[MenuItem]) {

  def calculatePreSCTotal: BigDecimal = {
    items.map{_.cost}.sum
  }

  def calculateServiceCharge: BigDecimal = {
    if (items.map(item => item.foodType).contains(Food)) {
      if (items.filter(_.foodType == Food).map(item => item.temperature).contains(Hot)) {
        val hotFoodSC = calculatePreSCTotal * 0.2
        if (hotFoodSC > 20) 20
        else hotFoodSC
      }
      else calculatePreSCTotal * 0.1
    } else 0
  }
}
