package org.digitinary.traninng.librarymanagmentsystem.dto;

import lombok.Getter;
import lombok.Setter;
import org.digitinary.traninng.librarymanagmentsystem.enums.BookType;

@Getter
@Setter
public class LoanPageDto {
    int offset;
    int pageSize;
    String sortFiled;
}
