package processingordermessage.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity(name = "order_event")
public class OrderEvent extends BaseEntity {
    @Column(nullable = false, length = 64)
    String orderNo;

    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    OrderEventType orderEventType;

    protected OrderEvent() {
    }

    public OrderEvent(String orderNo, OrderEventType orderEventType) {
        this.orderNo = orderNo;
        this.orderEventType = orderEventType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderEventType getOrderEventType() {
        return orderEventType;
    }

    public OrderEvent updateOrderEventType(OrderEventType orderEventType) {
        this.orderEventType = orderEventType;
        return this;
    }
}
