package menuitems

case object MargaritaPizza extends MenuItem {
  val name: String = "Margarita Pizza"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 6.75
  val foodType: FoodType = Food
  val premium: Boolean = false
}
