package org.digitinary.traninng.librarymanagmentsystem.unit;


import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.digitinary.traninng.librarymanagmentsystem.mapper.BookMapper;
import org.digitinary.traninng.librarymanagmentsystem.mapper.UserMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.BookModel;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.LoanRepository;
import org.digitinary.traninng.librarymanagmentsystem.service.BookService;
import org.digitinary.traninng.librarymanagmentsystem.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookService bookService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private LoanService loanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    public void createLoan_shouldThrowExceptionWhenBookNotInStock() {

        Long bookId = 1L;
        UserModel userModel = new UserModel();
        Loan loan = new Loan();

        when(bookService.isInStock(bookId)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                loanService.createLoan(loan, userModel, bookId)
        );

        assertEquals("Book Already Loaned", thrown.getMessage());

    }

    @Test
    public void getLoanById_shouldReturnLoan() {

        Long loanId = 1L;
        Loan loan = new Loan();

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));


        Loan result = loanService.getLoanById(loanId);


        assertEquals(loan, result);
    }

    @Test
    public void getLoanById_shouldThrowExceptionWhenLoanNotFound() {

        Long loanId = 1L;

        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());


        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                loanService.getLoanById(loanId)
        );

        assertEquals("No loan found with id: " + loanId, thrown.getMessage());
    }

    @Test
    public void deleteLoanById_shouldDeleteLoanAndUpdateBook() {

        Long loanId = 1L;
        Loan loan = new Loan();
        Book book = new Book();
        loan.setBook(book);

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        doNothing().when(bookService).UpdateBook(any(Book.class)); // Handle void method


        loanService.deleteLoanById(loanId);


        verify(loanRepository).delete(loan);
        verify(bookService).UpdateBook(book);
    }

    @Test
    public void updateLoan_shouldUpdateLoan() {

        Loan loan = new Loan();

        loanService.updateLoan(loan);

        verify(loanRepository).save(loan);
    }
}
