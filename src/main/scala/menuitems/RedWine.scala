package menuitems

case object RedWine extends MenuItem {
  val name: String = "Red Wine"
  val temperature: Temperature = Cold
  val cost: BigDecimal = 3.5
  val foodType: FoodType = Drink
  val premium: Boolean = false
}
