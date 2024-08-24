package org.digitinary.traninng.librarymanagmentsystem.service;


import org.digitinary.traninng.librarymanagmentsystem.dto.BookPageDTO;
import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.enums.BookType;
import org.digitinary.traninng.librarymanagmentsystem.mapper.BookMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.BookModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.BookRepository;


import org.digitinary.traninng.librarymanagmentsystem.util.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<BookModel> getAllBooksSortedByField(String filed) {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC,filed)).stream()
                .map(bookMapper::bookToBookModel)
                .collect(Collectors.toList());
    }

    public List<BookModel> getBooksWithPagination(int offset,int pageSize){
        return bookRepository.findAll(PageRequest.of(offset,pageSize))
                .stream()
                .map(bookMapper::bookToBookModel)
                .collect(Collectors.toList());
    }

    public List<BookModel> getBooksByTypeWithPaginationAndSorting(BookPageDTO bookPageDTO) {
        Pageable pageable = PageRequest.
                of(bookPageDTO.getOffset(),
                        bookPageDTO.getPageSize(),
                        Sort.by(bookPageDTO.getSortFiled())
                                .ascending());
        Page<Book> page = bookRepository.
                findByBookType(bookPageDTO.getType(), pageable);

        return page.stream()
                .map(bookMapper::bookToBookModel)
                .collect(Collectors.toList());
    }

    public void addBook(BookModel bookModel) {
        bookRepository.save(bookMapper.bookModelToBook(bookModel));
    }

    public BookModel getBookById(Long id) {
     Book book = bookRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Book not found with id: " + id));
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

    public List<BookModel> getBooksWhereAuthorNameLike(String name){
        return bookRepository.findAll(BookSpecification.getBookWithAuthorNameLike(name),
                Sort.by("author")).stream().map(bookMapper::bookToBookModel).collect(
                        Collectors.toList()
        );
    }

}
