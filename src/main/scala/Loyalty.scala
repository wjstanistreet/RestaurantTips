case class Loyalty(numOfStars: Int = 0) {
  val discount: Double = numOfStars match {
    case stars if stars <= 2 => 0
    case 3 => 0.075
    case 4 => 0.1
    case 5 => 0.125
    case 6 => 0.15
    case 7 => 0.175
    case stars if stars >= 8 => 0.2
  }
}