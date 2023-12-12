package menuitems

case object Carbonara extends MenuItem {
  val name: String = "Carbonara"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 8.5
  val foodType: FoodType = Food
  val premium: Boolean = false
}
