package org.digitinary.traninng.librarymanagmentsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@NoArgsConstructor
public class Loan {
 private Long id;
 @JsonBackReference
 private User user;
 @JsonManagedReference
 private Book book;
 private LocalDate formDate;
 private LocalDate returnDate;

    public Loan(User user, Book book, LocalDate formDate, LocalDate returnDate) {
        this.user = user;
        this.book = book;
        this.formDate = formDate;
        this.returnDate = returnDate;
    }

    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
     @SequenceGenerator(name = "loan_id_generator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Column(name="from_date")
    public LocalDate getFormDate() {
        return formDate;
    }

    public void setFormDate(LocalDate formDate) {
        this.formDate = formDate;
    }
    @Column(name = "return_date")
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public void addUserToLoan(User user) {
        this.user = user;
    }
    public void removeUserFromLoan(User user) {
        this.user = null;
    }
    public void removeBookFromLoan(Book book) {
        this.book = null;
    }
    public void addLoanFromBook(Book book) {
        this.book = book;
    }
}
