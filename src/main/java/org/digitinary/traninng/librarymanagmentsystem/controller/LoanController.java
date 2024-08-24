package org.digitinary.traninng.librarymanagmentsystem.controller;

import org.digitinary.traninng.librarymanagmentsystem.dto.LoanPageDto;
import org.digitinary.traninng.librarymanagmentsystem.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/notifyLateUsers")
    public void notifyLateUsers() {
        loanService.checkAndNotifyOverdueLoans();
    }

    @GetMapping("/page")
    public ResponseEntity<?> getLoanWithPage(@RequestParam LoanPageDto pageDTO) {
        return ResponseEntity.ok(loanService.getLoansWithPaginationAndSorting
                (pageDTO));
    }

}
