package org.digitinary.traninng.librarymanagmentsystem.service;


import org.digitinary.traninng.librarymanagmentsystem.dto.UserPageDto;
import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.digitinary.traninng.librarymanagmentsystem.mapper.UserMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.UserRepository;
import org.digitinary.traninng.librarymanagmentsystem.repository.specification.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<UserModel> getUsersSortedByName() {
        return userRepository.findAllByOrderByNameAsc().stream()
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
    public Page<UserModel> getUsersWithSpecificEmailType(UserPageDto pageDto, String emailType) {
        Pageable pageable = PageRequest.of(
                pageDto.getOffset(),
                pageDto.getPageSize(),
                Sort.by(pageDto.getSortFiled())
        );


        return userRepository.findAll(UserSpecification.hasEmailLike(emailType), pageable)
                .map(userMapper::userToUserModel);
    }


    public List<UserModel> getUsersWithSpicificName(UserPageDto pageDto,String name) {
        Pageable pageable = PageRequest.of(pageDto.getOffset(), pageDto.getPageSize(),Sort.by(pageDto.getSortFiled()));
        return userRepository.findAll(UserSpecification.hasNameLike(name),pageable)
                .stream().map(userMapper::userToUserModel).toList();
    }
    public List<UserModel> getUsersWithSpicificPhoneCode(UserPageDto pageDto, String code) {
        Pageable pageable = PageRequest.of(pageDto.getOffset(), pageDto.getPageSize(),Sort.by(pageDto.getSortFiled()));
        return userRepository.findAll(UserSpecification.hasPhoneLike(code),pageable)
                .stream().map(userMapper::userToUserModel).toList();
    }

}
