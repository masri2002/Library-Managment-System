package org.digitinary.traninng.librarymanagmentsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.digitinary.traninng.librarymanagmentsystem.enums.BookType;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
       private Long id;
       private String title;
       private String author;
       private String publisher;
       private String isbn;
       @JsonBackReference
       private Set<Loan> loans = new HashSet<>();
       private BookType bookType;
       private boolean inStock;

    public Book(String title, String author, String publisher, String isbn, BookType bookType, boolean inStock) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.bookType = bookType;
        this.inStock = inStock;
    }

    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
     @SequenceGenerator(name = "book_id_generator", allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
     @Column(name = "book_name")
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name="author_name")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
     @Column(name = "publisher_name")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    @Column(name = "book_type")
    @Enumerated(EnumType.STRING)
    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
    @OneToMany(mappedBy = "book")
    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }
    @Column(name = "available")
    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
