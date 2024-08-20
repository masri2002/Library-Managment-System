package org.digitinary.traninng.librarymanagmentsystem.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.digitinary.traninng.librarymanagmentsystem.enums.BookType;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookModel {
    private Long id;
    @NotEmpty(message = "title cannot be empty")
    private String title;
    @NotEmpty(message = "Author name cannot be empty")
    private String author;
    @NotEmpty(message = "Publisher name cannot be empty")
    private String publisher;
    @NotEmpty(message = "Isbn cannot be empty")
    private String isbn;
    @NotNull(message = "book should have type")
    private BookType bookType;
    private boolean inStock;


}
