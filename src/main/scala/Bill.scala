case class Bill(val items: List[MenuItem]) {

  def calculateTotal: BigDecimal = {
    items.map{item => item.cost}.sum
  }
}
