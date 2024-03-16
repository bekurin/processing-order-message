package processingordermessage.core.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import processingordermessage.core.entity.Order
import processingordermessage.core.entity.OrderEvent
import processingordermessage.core.entity.OrderEventRepository
import processingordermessage.core.entity.OrderEventType
import processingordermessage.core.entity.OrderEventType.DELIVERY_COMPLETED
import processingordermessage.core.entity.OrderEventType.ITEM_SHIPPED
import processingordermessage.core.entity.OrderEventType.PAYMENT_ATTEMPT
import processingordermessage.core.entity.OrderEventType.PAYMENT_COMPLETED
import processingordermessage.core.entity.OrderEventType.PAYMENT_FAIL
import processingordermessage.core.entity.OrderRepository

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderEventRepository: OrderEventRepository
) {
    @Transactional
    fun createOrder(orderNo: String, address: String, orderItem: String): OrderEvent {
        val order = Order(orderNo, address, orderItem)
        val orderEvent = OrderEvent(orderNo, PAYMENT_ATTEMPT)
        orderRepository.save(order)
        return orderEventRepository.save(orderEvent)
    }

    @Transactional
    fun fail(orderNo: String): OrderEvent {
        return findAndUpdateOrderEvent(orderNo, PAYMENT_FAIL)
    }

    @Transactional
    fun complete(orderNo: String): OrderEvent {
        return findAndUpdateOrderEvent(orderNo, PAYMENT_COMPLETED)
    }

    @Transactional
    fun shipped(orderNo: String): OrderEvent {
        return findAndUpdateOrderEvent(orderNo, ITEM_SHIPPED)
    }

    @Transactional
    fun delivered(orderNo: String): OrderEvent {
        return findAndUpdateOrderEvent(orderNo, DELIVERY_COMPLETED)
    }

    private fun findAndUpdateOrderEvent(orderNo: String, orderEventType: OrderEventType): OrderEvent {
        val orderEvent = orderEventRepository.findByOrderNo(orderNo)
            .orElseThrow { throw RuntimeException("결제 정보를 찾을 수 없습니다.") }
        return orderEvent.updateOrderEventType(orderEventType)
    }
}
