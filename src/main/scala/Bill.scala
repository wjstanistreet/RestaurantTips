case class Bill(items: List[MenuItem], total: BigDecimal = 0) {

  def calculatePreTaxTotal: BigDecimal = {
    items.map{_.cost}.sum
  }

  def calculateServiceCharge: BigDecimal = {
    null
  }
}
