package processingordermessage.serviceapi.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import processingordermessage.core.service.OrderFacadeService
import processingordermessage.serviceapi.controller.reponse.OrderResponse
import processingordermessage.serviceapi.controller.request.OrderCreationRequest

@RestController
@RequestMapping("/api/v1")
class OrderController(
    private val orderFacadeService: OrderFacadeService
) {
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(
        @RequestBody request: OrderCreationRequest
    ): OrderResponse {
        val createdOrder = orderFacadeService.createOrder(request.address, request.orderItem)
        return OrderResponse(createdOrder)
    }

    @PostMapping("/orders/{orderNo}/shipped")
    fun shipped(
        @PathVariable orderNo: String
    ): OrderResponse {
        val shippedOrderEvent = orderFacadeService.shipped(orderNo)
        return OrderResponse(shippedOrderEvent)
    }

    @PostMapping("/orders/{orderNo}/delivered")
    fun delivered(
        @PathVariable orderNo: String
    ): OrderResponse {
        val deliveredOrderEvent = orderFacadeService.delivered(orderNo)
        return OrderResponse(deliveredOrderEvent)
    }
}
