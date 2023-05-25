package com.example.eMedicineStore.service;

import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(Long userId);

    User addUser(User user);

    User updateUserById(User user, Long userId) throws UserNotFoundException;

    String deleteUserById(Long userId);
}
