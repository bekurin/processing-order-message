package processingordermessage.core.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import processingordermessage.core.entity.OrderEvent
import processingordermessage.core.util.SnowflakeIdGenerator
import java.util.*

@Service
class OrderFacadeService(
    private val orderService: OrderService,
    private val payService: PayService
) {
    private val log = LoggerFactory.getLogger(OrderFacadeService::class.java)
    private val generator = SnowflakeIdGenerator(Random(100).nextLong())

    fun createOrder(address: String, orderItem: String): OrderEvent {
        val orderNo = "A${generator.nextId()}"
        val createdOrderEvent = orderService.createOrder(orderNo, address, orderItem)
        pay(orderNo)
        return createdOrderEvent
    }

    fun shipped(orderNo: String): OrderEvent {
        return orderService.shipped(orderNo)
    }

    fun delivered(orderNo: String): OrderEvent {
        return orderService.delivered(orderNo)
    }

    @Async
    fun pay(orderNo: String) {
        try {
            payService.pay(orderNo)
            orderService.complete(orderNo)
        } catch (exception: Exception) {
            log.error("결제에 실패했습니다. 결제 실패로 변경합니다.")
            orderService.fail(orderNo)
        }
    }
}
