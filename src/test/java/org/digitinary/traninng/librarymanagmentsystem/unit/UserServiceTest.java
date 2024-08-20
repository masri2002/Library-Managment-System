package org.digitinary.traninng.librarymanagmentsystem.unit;


import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.digitinary.traninng.librarymanagmentsystem.mapper.UserMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.UserRepository;
import org.digitinary.traninng.librarymanagmentsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

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
        when(userRepository.findAll()).thenReturn(Stream.of(user).collect(Collectors.toList()));
        when(userMapper.userToUserModel(user)).thenReturn(userModel);


        List<UserModel> result = userService.getUsers();


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
}
