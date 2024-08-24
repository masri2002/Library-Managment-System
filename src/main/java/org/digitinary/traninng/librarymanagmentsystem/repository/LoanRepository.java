package org.digitinary.traninng.librarymanagmentsystem.repository;

import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.*;
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Page<Loan> findByReturnDateBefore(LocalDate returnDate, Pageable pageable);
    List<Loan> findByReturnDateBefore(LocalDate date);

}
