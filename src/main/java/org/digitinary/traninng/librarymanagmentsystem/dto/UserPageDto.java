package org.digitinary.traninng.librarymanagmentsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPageDto {
    int offset;
    int pageSize;
    String sortFiled;
}
