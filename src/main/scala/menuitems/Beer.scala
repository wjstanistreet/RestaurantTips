package menuitems

case object Beer extends MenuItem {
  val name: String = "Beer"
  val temperature: Temperature = Cold
  val cost: BigDecimal = 2.75
  val foodType: FoodType = Drink
  val premium: Boolean = false
}
