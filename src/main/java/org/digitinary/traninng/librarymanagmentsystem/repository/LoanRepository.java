package org.digitinary.traninng.librarymanagmentsystem.repository;

import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
