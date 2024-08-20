package org.digitinary.traninng.librarymanagmentsystem.mapper;

import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "title", target = "title")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "publisher", target = "publisher")
    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "bookType", target = "bookType")
    @Mapping(source = "inStock", target = "inStock")
    Book bookModelToBook(BookModel bookModel);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "publisher", target = "publisher")
    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "bookType", target = "bookType")
    @Mapping(source = "inStock", target = "inStock")
    BookModel bookToBookModel(Book book);
}
