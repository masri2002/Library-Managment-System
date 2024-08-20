package org.digitinary.traninng.librarymanagmentsystem.notifire;


import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.digitinary.traninng.librarymanagmentsystem.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements Subscriber {
    private final EmailService service;

    public EmailNotificationService(EmailService service) {
        this.service = service;
    }

    @Override
    public void update(Loan loan) {
        String message = "Dear " + loan.getUser().getName() + ",\n"
                + "Your loan for the book \"" + loan.getBook().getTitle() + "\" was due on " + loan.getReturnDate() + ". Please return it as soon as possible.";
        sendEmail(loan.getUser().getEmail(), "Loan Overdue Notice", message);
    }

    private void sendEmail(String to, String subject, String body) {
          service.sendEmail(to,subject,body);
    }
}

