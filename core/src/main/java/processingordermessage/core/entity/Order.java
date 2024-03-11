package processingordermessage.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Order extends BaseEntity {

    @Column(nullable = false, length = 63)
    String orderNo;

    @Column(nullable = false, length = 127)
    String address;

    @Column(nullable = false, length = 127)
    String orderItem;

    public Order(String orderNo, String address, String orderItem) {
        this.orderNo = orderNo;
        this.address = address;
        this.orderItem = orderItem;
    }

    protected Order() {
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getAddress() {
        return address;
    }

    public String getOrderItem() {
        return orderItem;
    }
}
