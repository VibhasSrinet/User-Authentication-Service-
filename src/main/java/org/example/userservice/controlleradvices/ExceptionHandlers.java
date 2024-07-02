package org.example.userservice.controlleradvices;

import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.exceptions.PasswordIncorrectException;
import org.example.userservice.exceptions.UserNotFoundException;
import org.example.userservice.dtos.PasswordIncorrectDto;
import org.example.userservice.dtos.UserNotFoundDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundDto> handleUserNotFoundException(UserNotFoundException exception) {
        UserNotFoundDto response = new UserNotFoundDto(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<PasswordIncorrectDto> handlePasswordIncorrectException(PasswordIncorrectException exception) {
        PasswordIncorrectDto response = new PasswordIncorrectDto(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<UserNotFoundDto> handleInvalidTokenException(InvalidTokenException exception) {
        UserNotFoundDto response = new UserNotFoundDto(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
