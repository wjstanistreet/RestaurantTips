package menuitems

case object Ramen extends MenuItem {
  val name: String = "Ramen"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 0.5
  val foodType: FoodType = Drink
  val premium: Boolean = false
}

