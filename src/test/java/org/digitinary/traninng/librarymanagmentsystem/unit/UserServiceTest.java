package org.digitinary.traninng.librarymanagmentsystem.unit;


import org.digitinary.traninng.librarymanagmentsystem.LibraryManagementSystemApplication;
import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.digitinary.traninng.librarymanagmentsystem.mapper.UserMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.UserRepository;
import org.digitinary.traninng.librarymanagmentsystem.service.BookService;
import org.digitinary.traninng.librarymanagmentsystem.service.LoanService;
import org.digitinary.traninng.librarymanagmentsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanService loanService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        User user = new User(1L, "John Doe", "john.doe@example.com", "1234567890");
        UserModel userModel = new UserModel(1, "John Doe", "john.doe@example.com", "1234567890");

        // Mock the behavior of the repository and mapper
        when(userRepository.findAll()).thenReturn(Stream.of(user).collect(Collectors.toList()));
        when(userMapper.userToUserModel(user)).thenReturn(userModel);

        // Act: Call the service method to get the list of user models
        List<UserModel> result = userService.getUsers();

        // Assert: Verify the results and interactions
        assertEquals(1, result.size());
        assertEquals(userModel, result.get(0));
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).userToUserModel(user);
    }

    @Test
    void testAddUser() {

        UserModel userModel = new UserModel(1, "John Doe", "john.doe@example.com", "1234567890");
        User user = new User(1L, "John Doe", "john.doe@example.com", "1234567890");
        when(userMapper.userModelToUser(userModel)).thenReturn(user);

        UserModel result = userService.addUser(userModel);

        assertEquals(userModel, result);
        verify(userMapper, times(1)).userModelToUser(userModel);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void updateLoanDate_shouldUpdateLoanReturnDate() {
        // Arrange
        Long userId = 1L;
        Long loanId = 2L;
        LocalDate newReturnDate = LocalDate.of(2024, 8, 31);

        User user = new User();
        user.setId(userId);

        Loan loan = new Loan();
        loan.setId(loanId);
        loan.setReturnDate(LocalDate.of(2024, 8, 1));

        Set<Loan> loans = new HashSet<>();
        loans.add(loan);
        user.setLoans(loans);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.updateLoanDate(userId, loanId, newReturnDate);

        // Assert
        verify(userRepository).findById(userId);
        verify(loanService).updateLoan(loan);

        // Check if the loan's return date was updated
        assert loan.getReturnDate().equals(newReturnDate);
    }

    @Test
    public void updateLoanDate_shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        Long userId = 1L;
        Long loanId = 2L;
        LocalDate newReturnDate = LocalDate.of(2024, 8, 31);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown =
                org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () ->
                        userService.updateLoanDate(userId, loanId, newReturnDate)
                );

        org.junit.jupiter.api.Assertions.assertEquals("User not found", thrown.getMessage());
    }

    @Test
    public void updateLoanDate_shouldThrowExceptionWhenLoanNotFound() {
        // Arrange
        Long userId = 1L;
        Long loanId = 2L;
        LocalDate newReturnDate = LocalDate.of(2024, 8, 31);

        User user = new User();
        user.setId(userId);

        Set<Loan> loans = new HashSet<>();
        user.setLoans(loans);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act & Assert
        RuntimeException thrown =
                org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () ->
                        userService.updateLoanDate(userId, loanId, newReturnDate)
                );

        org.junit.jupiter.api.Assertions.assertEquals("Loan not found", thrown.getMessage());
    }
}
