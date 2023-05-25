package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.exception.UserNotFoundException;
import com.example.eMedicineStore.repository.UserRepository;
import com.example.eMedicineStore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        logger.info("Found user successfully!");
        return userRepository.findById(userId).get();
    }

    @Override
    public User addUser(User user) {
        logger.info("Added user successfully!");
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(User user, Long userId) throws UserNotFoundException {
        Optional<User> user1 = userRepository.findById(userId);

        if (user1.isPresent()){
            //code for update
            if (!user1.get().getUserName().equals(user.getUserName())){
                user1.get().setUserName(user.getUserName());
            }
            if (!user1.get().getEmail().equals(user.getEmail())){
                user1.get().setEmail(user.getEmail());
            }
            if (!user1.get().getPassword().equals(user.getPassword())){
                user1.get().setPassword(user.getPassword());
            }
            if (!user1.get().getPhoneNum().equals(user.getPhoneNum())){
                user1.get().setPhoneNum(user.getPhoneNum());
            }
            logger.info("User is found");
        } else {
            logger.error("No user found with this ID.");
            throw new UserNotFoundException("No user found with this ID.");
        }
        return userRepository.save(user1.get());
    }

    @Override
    public String deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        return "Deletion successfully";
    }
}
