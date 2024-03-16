package processingordermessage.core.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {

    Optional<OrderEvent> findByOrderNo(String orderNo);
}
