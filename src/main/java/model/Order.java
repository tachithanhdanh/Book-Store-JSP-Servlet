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
    private String orderStatus; // "new", "processing", "delivering", "delivered", "return"
    private String paymentMethod;
    private String paymentStatus;
    private double totalAmountPaid;
    private double remainingAmountDue;
    private Date orderDate;
    private Date deliveryDate;
}
