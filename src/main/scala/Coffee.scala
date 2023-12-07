case object Coffee extends MenuItem {
  val name: String = "Coffee"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 1.0
  val foodType: FoodType = Drink
}