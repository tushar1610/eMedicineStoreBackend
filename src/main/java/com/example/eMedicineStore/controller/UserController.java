package com.example.eMedicineStore.controller;

import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserById/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/updateUserById/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User user){
        return userService.updateUserById(user, userId);
    }
    @DeleteMapping("/deleteUserById/{userId}")
    public String deleteUserById(@PathVariable Long userId){
        return userService.deleteUserById(userId);
    }

}
