package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Book {
    private String bookId;
    private String bookTitle;
    private Author author;
    private int publicationYear;
    private double importPrice;
    private double listPrice;
    private double sellingPrice;
    private int quantity;
    private Category category;
    private String description;
}
