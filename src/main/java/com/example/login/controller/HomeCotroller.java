package com.example.login.controller;

import com.example.login.entity.User;
import com.example.login.exception.InvalidPasswordException;
import com.example.login.exception.InvalidUsernameException;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeCotroller {
    private final UserService userService;

    @Autowired
    public HomeCotroller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            userService.authentication(user);
        } catch (InvalidUsernameException invalidUsernameException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not found user");
        } catch (InvalidPasswordException invalidPasswordException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("sai pass");
        }
        return ResponseEntity.status(HttpStatus.OK).body("dang nhap thanh cong");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
        } catch (InvalidUsernameException invalidUsernameException) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sai me m roi");
        } catch (InvalidPasswordException invalidPasswordException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sai pass roi");
        }
        return ResponseEntity.ok("Qua ok");
    }

}
