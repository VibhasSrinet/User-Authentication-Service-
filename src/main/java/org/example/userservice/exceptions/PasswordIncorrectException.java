package org.example.userservice.exceptions;

public class PasswordIncorrectException extends Exception{
    public PasswordIncorrectException(String message){
        super(message);
    }
}
