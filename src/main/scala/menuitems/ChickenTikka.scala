package menuitems

case object ChickenTikka extends MenuItem {
  val name: String = "Chicken Tikka"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 6.5
  val foodType: FoodType = Food
  val premium: Boolean = false
}
