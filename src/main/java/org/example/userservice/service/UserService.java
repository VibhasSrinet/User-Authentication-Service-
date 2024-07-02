package org.example.userservice.service;

import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.exceptions.PasswordIncorrectException;
import org.example.userservice.exceptions.UserNotFoundException;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;

public interface UserService {
    public User signUp(String fullName, String email, String password);
    public Token login(String email, String password) throws UserNotFoundException, PasswordIncorrectException;
    void logout(String token) throws InvalidTokenException;
    User validateToken(String token) throws InvalidTokenException;
}
