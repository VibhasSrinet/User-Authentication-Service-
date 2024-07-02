package org.example.userservice.service;


import org.apache.commons.lang3.RandomStringUtils;
import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.exceptions.PasswordIncorrectException;
import org.example.userservice.exceptions.UserNotFoundException;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repository.TokenRepository;
import org.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class SelfUserService implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    @Autowired
    public SelfUserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository= tokenRepository;
    }

    @Override
    public User signUp(String fullName, String email, String password) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException("email already exists!");
        });
        User user = new User();
        user.setEmail(email);
        user.setName(fullName);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) throws UserNotFoundException, PasswordIncorrectException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("email does not exist!");
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            throw new PasswordIncorrectException("password is incorrect!");
        }
        Token token = new Token();
        token.setUser(user);
        Calendar calendar = Calendar.getInstance();

        // Adds 30 days to the current date
        calendar.add(Calendar.DAY_OF_MONTH, 30);

        // Converts the calendar object into a Date object
        Date date = calendar.getTime();
        token.setExpireAt(date);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        Token savedToken = tokenRepository.save(token);
        return token;
    }

    @Override
    public void logout(String token) throws InvalidTokenException {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeleted(token, false);
        if(tokenOptional.isEmpty()){
            throw new InvalidTokenException("the token doesn't exist or deleted!");
        }

        Token token1 = tokenOptional.get();
        token1.setDeleted(true);
        tokenRepository.save(token1);
    }

    @Override
    public User validateToken(String token) throws InvalidTokenException {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeletedAndExpireAtGreaterThan(
                token,
                false,
                new Date());
        if(tokenOptional.isEmpty()){
            throw new InvalidTokenException("the token doesn't exist or expired!");
        }
        return tokenOptional.get().getUser();
    }
}
