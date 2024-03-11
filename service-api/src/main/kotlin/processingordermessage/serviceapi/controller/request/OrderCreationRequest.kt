package processingordermessage.serviceapi.controller.request

data class OrderCreationRequest(
    val address: String,
    val orderItem: String
)
