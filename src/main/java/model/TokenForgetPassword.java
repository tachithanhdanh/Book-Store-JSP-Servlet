package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TokenForgetPassword {
    private long id;
    private Customer customer;
    private String token;
    private LocalDateTime expiryDate;
    private boolean isUsed;
}
