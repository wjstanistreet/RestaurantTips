package menuitems

case object Chai extends MenuItem {
  val name: String = "Chai"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 1
  val foodType: FoodType = Drink
  val premium: Boolean = false
}
