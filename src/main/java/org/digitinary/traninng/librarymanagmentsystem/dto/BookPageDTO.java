package org.digitinary.traninng.librarymanagmentsystem.dto;

import lombok.Getter;
import lombok.Setter;
import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.enums.BookType;

@Getter
@Setter
public class BookPageDTO {
    int offset;
    int pageSize;
    BookType type;
    String sortFiled;
}
