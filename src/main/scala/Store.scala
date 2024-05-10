object Store {
  final case class Order(id: Int, description: String)

  final case class DeliveryDetails(id: Int, address: String)

  final case class Fulfillment(deliveryDate: String)

  def getOrder(userId: Int): Option[Order] =
    userId match {
      case 0 => Some(Order(0, "Assorted seashells"))
      case 1 => Some(Order(1, "Fromage"))
      case 2 => Some(Order(2, "Hard tack"))
      case _ => None
    }

  def getDeliveryDetails(orderId: Int): Option[DeliveryDetails] =
    orderId match {
      case 0 => Some(DeliveryDetails(6, "Buckingham Palace"))
      case 1 => Some(DeliveryDetails(9, "21 Jump Street"))
      case _ => None
    }

  def getFulfillment(
                      order: Order,
                      delivery: DeliveryDetails
                    ): Option[Fulfillment] =
    (order.id, delivery.id) match {
      case (_, 6) => None
      case (1, _) => Some(Fulfillment("Today"))
      case (2, _) => Some(Fulfillment("Tomorrow, if you're lucky"))
      case _ => None
    }

  def processOrderForUser(userId: Int): Option[Fulfillment] =
    for {
      order <- getOrder(userId)
      details <- getDeliveryDetails(order.id)
      fulfillment <- getFulfillment(order, details)
    } yield fulfillment
}
