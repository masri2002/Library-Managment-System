package org.digitinary.traninng.librarymanagmentsystem.service;

import jakarta.transaction.Transactional;
import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.digitinary.traninng.librarymanagmentsystem.mapper.BookMapper;
import org.digitinary.traninng.librarymanagmentsystem.mapper.UserMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.BookModel;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
 private final LoanRepository loanRepository;
 private final BookService bookService;
 private final UserMapper userMapper;
 private final BookMapper bookMapper;

    public LoanService(LoanRepository loanRepository, BookService bookService, UserMapper userMapper, BookMapper bookMapper) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }
    @Transactional
    public void createLoan(Loan loan, UserModel userModel, Long id) {
        if(bookService.isInStock(id)){
            BookModel model=bookService.getBookById(id);
            model.setInStock(false);
            bookService.UpdateBook(bookMapper.bookModelToBook(model));
            loan.addLoanFromBook(bookMapper.bookModelToBook(
                    bookService.getBookById(id)));
        }else{
            throw new RuntimeException("Book Already Loaned");
        }
        loan.addUserToLoan(userMapper.userModelToUser(userModel));
        loanRepository.save(loan);
    }
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(()-> new RuntimeException("No loan found with id: " + id));
    }
    @Transactional
    public void deleteLoanById(Long id) {
        Loan loan = getLoanById(id);
        Book book =loan.getBook();
        book.setInStock(true);
        bookService.UpdateBook(book);
        loanRepository.delete(loan);

    }
    public void updateLoan(Loan loan){
        loanRepository.save(loan);
    }
}
