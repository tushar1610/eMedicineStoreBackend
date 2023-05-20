package com.example.eMedicineStore.service;

import com.example.eMedicineStore.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(Long userId);

    User addUser(User user);

    User updateUserById(User user, Long userId);

    String deleteUserById(Long userId);
}
