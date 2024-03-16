package processingordermessage.serviceapi.controller.reponse

import processingordermessage.core.entity.OrderEvent
import processingordermessage.core.entity.OrderEventType

data class OrderResponse(
    val orderNo: String,
    val orderEventType: OrderEventType
) {
    constructor(orderEvent: OrderEvent) : this(
        orderNo = orderEvent.orderNo,
        orderEventType = orderEvent.orderEventType
    )
}
