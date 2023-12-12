package menuitems

case object Sake extends MenuItem {
  val name: String = "Sake"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 3.5
  val foodType: FoodType = Drink
  val premium: Boolean = false
}
