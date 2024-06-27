package model;

import lombok.*;

import java.sql.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString @EqualsAndHashCode
public class Order {
    private String orderId;
    private Customer customer;
    private String billingAddress;
    private String shippingAddress;
    private String paymentMethod;
    private String orderStatus; // "new", "processing", "delivering", "delivered", "return"
    private String paymentStatus;
    private double paidAmount;
    private double remainingAmount;
    private Date orderDate;
    private Date deliveryDate;
}
