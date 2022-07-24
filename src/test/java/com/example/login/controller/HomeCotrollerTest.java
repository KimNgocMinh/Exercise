package com.example.login.controller;

import com.example.login.entity.User;
import com.example.login.repository.UserRepository;
import com.example.login.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


class HomeCotrollerTest {

    @InjectMocks
    private HomeCotroller homeCotroller;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository repository;

    @Test
    void login() {
        User user1 = new User("kiemnguyen","Kiem@123");
        User user2 = new User("43kiemnguyen","Kiem@123");
        User user3 = new User("kiemnguyen","Kiem@123a");
        User user4 = new User("3kiemnguyen","dKiem@123a");

    }

    @Test
    void createUser() {

    }
}