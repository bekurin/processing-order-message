package processingordermessage.serviceapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import processingordermessage.core.entity.OrderEventType

@RestController
class OrderController {
    @GetMapping("/hello")
    fun hello(): OrderEventType {
        return OrderEventType.ORDER_PROCESSING
    }
}
