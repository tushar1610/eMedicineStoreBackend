package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.exception.UserNotFoundException;
import com.example.eMedicineStore.repository.UserRepository;
import com.example.eMedicineStore.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    User user = User.builder()
            .userId(1L)
            .userName("user")
            .phoneNum("1234567890")
            .password("password")
            .email("email@gmai.com")
            .build();
    User user1 = User.builder()
            .userId(2L)
            .userName("user1")
            .phoneNum("1234567890")
            .password("password")
            .email("email1@gmai.com")
            .build();

    @Test
    void addUser() throws Exception {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User newUser = userService.addUser(user);
        assertEquals(user,newUser);
    }

    @Test
    void getUserById() throws Exception {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Long userId = 1L;
        User found = userService.getUserById(userId);
        assertEquals(found,user);
    }

    @Test
    void updateUser() throws UserNotFoundException {
        Long userId = 1L;
        User updatedUser = new User();
        updatedUser.setUserId(userId);
        updatedUser.setUserName("User 1");
        updatedUser.setPassword("newPass");
        updatedUser.setPhoneNum("8674567989");
        updatedUser.setEmail("email2@gmail.com");
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when((userRepository.save(user))).thenReturn(updatedUser);

        User result = userService.updateUserById(updatedUser, userId);
        assertEquals(updatedUser.getUserName(),result.getUserName());

    }

    @Test
    void deleteCustomer() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userService.deleteUserById(1L);
    }


}