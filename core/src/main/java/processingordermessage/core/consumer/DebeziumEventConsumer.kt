package processingordermessage.core.consumer

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import processingordermessage.core.entity.OrderEventRepository
import processingordermessage.core.event.DebeziumCaptureEvent
import java.util.function.Consumer

@Component
class DebeziumEventConsumer(
    private val orderEventRepository: OrderEventRepository
) : Consumer<DebeziumCaptureEvent> {

    private val log = LoggerFactory.getLogger(DebeziumEventConsumer::class.java)

    override fun accept(debeziumCaptureEvent: DebeziumCaptureEvent) {
        try {
            val payload = debeziumCaptureEvent.payload
            val source = payload.source
            val operationType = payload.operationType
            log.info("[DebeziumEventConsumer] payload=$payload, source=$source, operationType=$operationType")
        } catch (exception: Exception) {
            log.error("Failed to processing consumed message {}", debeziumCaptureEvent, exception)
            throw exception
        }
    }
}
