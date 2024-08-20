package org.digitinary.traninng.librarymanagmentsystem.service;


import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.mapper.BookMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.BookModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.BookRepository;


import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
private final BookRepository bookRepository;
private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookModel)
                .collect(Collectors.toList());
    }
    public void addBook(BookModel bookModel) {
        bookRepository.save(bookMapper.bookModelToBook(bookModel));
    }
    public BookModel getBookById(Long id) {
     Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return bookMapper.bookToBookModel(book);
    }
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
    public boolean isInStock(Long id){
        return bookRepository.inStockById(id);
    }
    public void UpdateBook(Book book){
        bookRepository.save(book);
    }

}
