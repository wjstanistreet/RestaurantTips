package menuitems

case object CacioEPepe extends MenuItem {
  val name: String = "Cacio E Pepe"
  val temperature: Temperature = Hot
  val cost: BigDecimal = 5.75
  val foodType: FoodType = Food
  val premium: Boolean = false
}
