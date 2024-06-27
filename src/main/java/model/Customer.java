package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Customer {
    private String customerId;
    private String username;
    private String password;
    private String fullName;
    private String gender;
    private String billingAddress;
    private String shippingAddress;
    private String invoiceAddress;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private boolean subscribeToNewsletter;
}
