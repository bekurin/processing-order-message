package processingordermessage.core.event

import com.fasterxml.jackson.annotation.JsonProperty
import java.lang.IllegalArgumentException

data class DebeziumCaptureEvent(
    val payload: Payload
)

data class Payload(
    val source: Source,
    val before: Map<String, Any>,
    val after: Map<String, Any>,
    @field:JsonProperty("op")
    val operationType: OperationType
)

data class Source(
    @field:JsonProperty("ts_ms")
    val transactionTime: Long,
    val db: String,
    val schema: String,
    val table: String
)

enum class OperationType(val code: String) {
    CREATE("c"),
    UPDATE("u"),
    DELETE("d"),
    READ("r");

    fun toOperationType(code: String): OperationType {
        return OperationType.entries.find { it.code == code }
            ?: throw IllegalArgumentException("Unknown operation type code: $code")
    }
}
