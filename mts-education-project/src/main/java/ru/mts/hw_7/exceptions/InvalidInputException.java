package ru.mts.hw_7.exceptions;

public class InvalidInputException extends IllegalArgumentException{
    public InvalidInputException(String message){
        super(message);
    }
}
