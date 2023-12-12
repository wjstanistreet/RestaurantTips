package menuitems

case object ButterChicken extends MenuItem {
  val name: String = "Butter Chicken"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 8.5
  val foodType: FoodType = Food
  val premium: Boolean = false
}
