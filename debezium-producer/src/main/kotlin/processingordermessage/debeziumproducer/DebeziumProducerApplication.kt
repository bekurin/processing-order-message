package processingordermessage.debeziumproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import processingordermessage.core.CoreApplication

@SpringBootApplication
@Import(CoreApplication::class)
class DebeziumProducerApplication

fun main(args: Array<String>) {
    runApplication<DebeziumProducerApplication>(*args)
}
