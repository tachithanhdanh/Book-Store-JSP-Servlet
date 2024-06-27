package model;

import lombok.*;

import java.sql.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class Author {
    private String authorId;
    private String fullName;
    private Date dateOfBirth;
    private String bio;
}
