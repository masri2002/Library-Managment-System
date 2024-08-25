package org.digitinary.traninng.librarymanagmentsystem.mapper;

import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "publisher", source = "publisher")
    @Mapping(target = "isbn", source = "isbn")
    @Mapping(target = "bookType", source = "bookType")
    @Mapping(target = "inStock", source = "inStock")
    @Mapping(target = "loans", ignore = true)
    Book bookModelToBook(BookModel bookModel);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "publisher", source = "publisher")
    @Mapping(target = "isbn", source = "isbn")
    @Mapping(target = "bookType", source = "bookType")
    @Mapping(target = "inStock", source = "inStock")
    BookModel bookToBookModel(Book book);
}
