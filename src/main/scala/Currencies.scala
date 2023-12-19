object Currencies {
  val exchangeRates: Map[String, BigDecimal] = Map(
    "GBP" -> 1,
    "USD" -> 1.26,
    "EUR" -> 1.16,
    "JPY" -> 182.71,
    "CNY" -> 9.02,
    "SGD" -> 1.69,
  )

  val symbol: Map[String, String] = Map(
    "GBP" -> "£",
    "USD" -> "$",
    "EUR" -> "€",
    "JPY" -> "¥",
    "CNY" -> "¥",
    "SGD" -> "$",
  )

  def getSymbol(currency: String): String = symbol(currency)
}
