package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.repository.UserRepository;
import com.example.eMedicineStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(User user, Long userId) {
        Optional<User> user1 = userRepository.findById(userId);

        if (user1.isPresent()){
            //code for update
        }
        return userRepository.save(user1.get());
    }

    @Override
    public String deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        return "Deletion successfully";
    }
}
