package com.example.login.service;

import com.example.login.entity.User;
import com.example.login.exception.InvalidPasswordException;
import com.example.login.exception.InvalidUsernameException;
import com.example.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.encoder = bCryptPasswordEncoder;
    }

    public void authentication(User unknown) throws InvalidUsernameException, InvalidPasswordException {
        User user = userRepository.findById(unknown.getUsername())
                .orElseThrow(() -> new InvalidUsernameException("Username not found"));
        if (!encoder.matches(unknown.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("sai pass");
        }
    }

    public void createUser (User user) throws InvalidUsernameException, InvalidPasswordException{
        if (!checkValidUsername(user.getUsername())) {
            throw new InvalidUsernameException("Sai username from create user");
        }
        if (!checkValidPassword(user.getPassword())) {
            throw new InvalidPasswordException("Sai pass from create user");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private static boolean checkValidUsername (String username) {
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z_]\\w{5,49}");
        return Pattern.matches(usernamePattern.pattern(),username);
    }

    private static boolean checkValidPassword(String password){
//        if (password.indexOf(' ') > -1 || password.length() < 8 || password.length() > 30 ) {
//            throw new InvalidPasswordException();
//        }
        Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[#$@%&*+-/`~.])(?!.*\\s).{8,}$");
        return Pattern.matches(passwordPattern.pattern(),password);
    }
}







