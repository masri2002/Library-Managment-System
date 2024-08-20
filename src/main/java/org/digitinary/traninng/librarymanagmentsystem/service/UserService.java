package org.digitinary.traninng.librarymanagmentsystem.service;


import lombok.Setter;
import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.digitinary.traninng.librarymanagmentsystem.mapper.UserMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
 private final UserRepository userRepository;
 private final UserMapper userMapper;
    private final LoanService loanService;

    public UserService(UserRepository userRepository, UserMapper userMapper,  LoanService loanService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.loanService = loanService;
    }

    public List<UserModel> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserModel)
                .collect(Collectors.toList());
    }
    public UserModel addUser(UserModel userModel) {
        userRepository.save(userMapper.userModelToUser(userModel));
        return userModel;
    }
   public void loanBook(Long userId, Long bookId, Loan loan){
        loanService.createLoan(loan,
               userMapper.userToUserModel( userRepository.findById(userId).orElseThrow())
                ,bookId);
   }
    public Set<Book> getLoanedBooks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getLoans().stream()
                .map(Loan::getBook)
                .collect(Collectors.toSet());
    }
    public void updateUser(UserModel userModel){
        userRepository.save(userMapper.userModelToUser(userModel));
    }
    public void updateLoanDate(Long userId, Long loanId, LocalDate newReturnDate) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found"));

        Loan loan = user.getLoans().stream()
                .filter(l -> l.getId().equals(loanId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(newReturnDate);

        loanService.updateLoan(loan);
    }

}
