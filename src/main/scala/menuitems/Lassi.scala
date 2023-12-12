package menuitems

case object Lassi extends MenuItem {
  val name: String = "Lassi"
  val temperature: Temperature = Cold
  val cost: BigDecimal = 1.5
  val foodType: FoodType = Drink
  val premium: Boolean = false
}
