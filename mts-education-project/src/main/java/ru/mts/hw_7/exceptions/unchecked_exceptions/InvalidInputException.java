package ru.mts.hw_7.exceptions.unchecked_exceptions;

public class InvalidInputException extends IllegalArgumentException{
    public InvalidInputException(String message){super(message);}
    public InvalidInputException(){super();}
}
