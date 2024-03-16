package processingordermessage.core.service

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class PayService {
    fun pay(orderNo: String) {
        val timeToWait = Random.nextLong(3000)
        Thread.sleep(timeToWait)
        if (orderNo.toLong() % 2 == 0L) {
            throw RuntimeException("결제 실패했습니다.")
        }
        throw RuntimeException("결제 실패했습니다.")
    }
}
