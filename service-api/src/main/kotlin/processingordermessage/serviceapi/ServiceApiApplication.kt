package processingordermessage.serviceapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import processingordermessage.core.CoreApplication

@SpringBootApplication
@Import(CoreApplication::class)
class ServiceApiApplication

fun main(args: Array<String>) {
    runApplication<ServiceApiApplication>(*args)
}
