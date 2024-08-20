package org.digitinary.traninng.librarymanagmentsystem.service;


import lombok.Setter;
import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.digitinary.traninng.librarymanagmentsystem.mapper.UserMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
 private final UserRepository userRepository;
 private final UserMapper userMapper;
 private final LoanService service;
    private final LoanService loanService;

    public UserService(UserRepository userRepository, UserMapper userMapper, LoanService service, LoanService loanService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.service = service;
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
}
