package org.digitinary.traninng.librarymanagmentsystem.controller;

import org.digitinary.traninng.librarymanagmentsystem.service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
