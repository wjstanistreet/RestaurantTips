import menuitems.{MenuItem, Theme}

case class Menu(cuisine: String = "Regular") {
  val items: Set[MenuItem] = Theme.cuisine(cuisine)
}