package org.example.userservice.controller;

import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.exceptions.PasswordIncorrectException;
import org.example.userservice.exceptions.UserNotFoundException;
import org.example.userservice.dtos.LoginRequestDto;
import org.example.userservice.dtos.LogoutRequestDto;
import org.example.userservice.dtos.SignUpRequestDto;
import org.example.userservice.dtos.UserDto;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignUpRequestDto request ) {
        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();
        return UserDto.from(userService.signUp(name, email, password));
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto request) throws UserNotFoundException, PasswordIncorrectException{
        String email = request.getEmail();
        String password = request.getPassword();
        return userService.login(email, password);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request) throws InvalidTokenException {
        userService.logout(request.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<UserDto> validateToken(@PathVariable("token") String token) throws InvalidTokenException {
        System.out.println("Token: " + token);
        return new ResponseEntity<>(UserDto.from(userService.validateToken(token)), HttpStatus.OK);
    }
}
