package menuitems

case object CheeseSandwich extends MenuItem {
  val name: String = "Cheese Sandwich"
  val temperature: Temperature = Cold
  val cost: BigDecimal = 2.0
  val foodType: FoodType = Food
  val premium: Boolean = false
}
