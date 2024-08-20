package org.digitinary.traninng.librarymanagmentsystem.controller;

import jakarta.validation.Valid;
import org.digitinary.traninng.librarymanagmentsystem.model.BookModel;
import org.digitinary.traninng.librarymanagmentsystem.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/book")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @PostMapping("/book")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookModel bookModel) {
        bookService.addBook(bookModel);
        return ResponseEntity.ok("Book added successfully");
    }

}
