package processingordermessage.core.consumer

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import processingordermessage.core.entity.OrderEventRepository
import java.util.function.Consumer

@Component
class DebeziumEventConsumer(
    private val orderEventRepository: OrderEventRepository
) : Consumer<String> {

    private val log = LoggerFactory.getLogger(DebeziumEventConsumer::class.java)

    override fun accept(debeziumCaptureEvent: String) {
        try {
            log.info("[DebeziumEventConsumer] debeziumCaptureEvent=$debeziumCaptureEvent")
        } catch (exception: Exception) {
            log.error("Failed to processing consumed message {}", debeziumCaptureEvent, exception)
            throw exception
        }
    }
}
