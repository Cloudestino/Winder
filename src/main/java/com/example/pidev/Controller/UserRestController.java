package com.example.pidev.Controller;

import com.example.pidev.Service.UserServiceC;
import com.example.pidev.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    UserServiceC userService;

    // http://localhost:8089/pidev/users/retrieve-all-users
    @GetMapping("/retrieve-all-users")
    public List<User> getUsers() {
        List<User> listUsers = userService.getAllUsers();
        return listUsers;
    }




    // http://localhost:8089/pidev/users/retrieve-user/8
    @GetMapping("/retrieve-user/{user-id}")
    public User retrieveUser(@PathVariable("user-id") Long UserId) {
        return userService.getUserById(UserId);
    }





    // http://localhost:8089/pidev/users/add-user
    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        User user1 = userService.createUser(user);
        return user1;
    }

    // http://localhost:8089/pidev/users/remove-user/1
    @DeleteMapping("/remove-user/{user-id}")
    public void removeUser(@PathVariable("user-id") Long userId) {
        userService.deleteUser(userId);
    }

    // http://localhost:8089/pidev/users/update-user
    @PutMapping("/update-user")
    public User updateUser(@RequestBody User user) {
        User user1 = userService.updateUser(user);
        return user1;
    }






















}
