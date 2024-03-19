package ru.mts.hw_7.exceptions;

public class InvalidDataException extends NullPointerException{
    public InvalidDataException(String message){
        super(message);
    }
}
