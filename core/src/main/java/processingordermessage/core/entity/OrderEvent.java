package processingordermessage.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class OrderEvent extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderNo")
    Order order;

    @Column(nullable = false, length = 64)
    OrderEventType orderEventType;

    protected OrderEvent() {
    }

    public OrderEvent(Order order, OrderEventType orderEventType) {
        this.order = order;
        this.orderEventType = orderEventType;
    }

    public Order getOrder() {
        return order;
    }

    public OrderEventType getOrderEventType() {
        return orderEventType;
    }

    public OrderEvent updateOrderEventType(OrderEventType orderEventType) {
        this.orderEventType = orderEventType;
        return this;
    }
}
