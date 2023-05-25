package com.example.eMedicineStore.controller;

import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.exception.UserNotCreatedException;
import com.example.eMedicineStore.exception.UserNotFoundException;
import com.example.eMedicineStore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/getUserById/{userId}")
    public User getUserById(@PathVariable Long userId) throws Exception{
        logger.info("Calling getUserById method");
        User user = userService.getUserById(userId);
        if (user == null){
            throw new UserNotFoundException("No user found for this id.");
        }
        return user;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) throws Exception{
        logger.info("Calling addUser method");
        User user1 = userService.addUser(user);
        if (user1 == null) {
            logger.error("User cannot be created");
            throw new UserNotCreatedException("User cannot be created. Try again later.");
        }
        return user;
    }

    @PutMapping("/updateUserById/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User user) throws UserNotFoundException {
        logger.info("Updated user successfully!");
        return userService.updateUserById(user, userId);
    }
    @DeleteMapping("/deleteUserById/{userId}")
    public String deleteUserById(@PathVariable Long userId){

        logger.info("Deleted user sucessfully!");
        return userService.deleteUserById(userId);
    }

}
