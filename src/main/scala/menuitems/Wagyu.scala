package menuitems

case object Wagyu extends MenuItem {
  val name: String = "Wagyu"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 35.0
  val foodType: FoodType = Food
  val premium: Boolean = true
}
