package menuitems

trait MenuItem {
  val name: String
  val temperature: Temperature
  val cost: BigDecimal
  val foodType: FoodType
  val premium: Boolean
}
