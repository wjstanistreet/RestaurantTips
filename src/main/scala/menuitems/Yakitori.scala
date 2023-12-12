package menuitems

case object Yakitori extends MenuItem {
  val name: String = "Yakitori"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 7.5
  val foodType: FoodType = Food
  val premium: Boolean = false
}
