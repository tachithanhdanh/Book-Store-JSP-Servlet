package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Author {
    private String authorId;
    private String fullName;
    private Date dateOfBirth;
    private String bio;
}
