package org.digitinary.traninng.librarymanagmentsystem.mapper;

import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book bookModelToBook(BookModel bookModel);

    BookModel bookToBookModel(Book book);
}
