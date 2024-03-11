package processingordermessage.core.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class PayService {
    fun pay(orderNo: String) {
        val timeToWait = Random.nextLong(3000)
        Thread.sleep(timeToWait)
        if (orderNo.last().isDigit()) {
            throw RuntimeException("결제 실패했습니다.")
        }
        throw RuntimeException("결제 실패했습니다.")
    }
}
