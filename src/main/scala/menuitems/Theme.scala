package menuitems

object Theme {
  val cuisine: Map[String, Set[MenuItem]] = Map(
    "Regular"   -> Set(Cola, Coffee, CheeseSandwich, SteakSandwich, Lobster),
    "Japanese"  -> Set(Sake, Beer, Ramen, Yakitori, Wagyu),
    "Italian"   -> Set(RedWine, Beer, Carbonara, MargaritaPizza, CacioEPepe),
    "Indian"    -> Set(Lassi, Chai, ChickenTikka, ButterChicken),
  )
}
