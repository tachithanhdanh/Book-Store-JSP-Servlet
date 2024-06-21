package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderDetail {
    private String orderDetailId;
    private Order order;
    private int bookId;
    private int quantity;
    private double listPrice;
    private double discount;
    private double sellingPrice;
    private double vat;
}

