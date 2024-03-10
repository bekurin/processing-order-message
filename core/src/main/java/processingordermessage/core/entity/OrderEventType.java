package processingordermessage.core.entity;

public enum OrderEventType {
    PAYMENT_ATTEMPT("결제 시도"),
    PAYMENT_COMPLETED("결제 완료"),
    ORDER_PROCESSING("상품 준비"),
    ITEM_SHIPPED("상품 배송"),
    DELIVERY_COMPLETED("배송 완료");

    OrderEventType(String description) {
    }
}
