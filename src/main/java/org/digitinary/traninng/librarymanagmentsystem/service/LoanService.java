package org.digitinary.traninng.librarymanagmentsystem.service;

import org.digitinary.traninng.librarymanagmentsystem.repository.LoanRepository;
import org.digitinary.traninng.librarymanagmentsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
 private final LoanRepository loanRepository;
 private final BookService bookService;

    public LoanService(LoanRepository loanRepository, BookService bookService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
    }

}
