package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
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
