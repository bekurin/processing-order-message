package processingordermessage.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Order extends BaseEntity {

    @Column(nullable = false, length = 64)
    String orderNo;

    public Order(Long id, String orderNo) {
        this.id = id;
        this.orderNo = orderNo;
    }

    protected Order() {
    }

    public String getOrderNo() {
        return orderNo;
    }
}
