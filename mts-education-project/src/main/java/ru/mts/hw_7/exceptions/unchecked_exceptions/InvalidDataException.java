package ru.mts.hw_7.exceptions.unchecked_exceptions;

public class InvalidDataException extends NullPointerException{
    public InvalidDataException(String message){
        super(message);
    }
    public InvalidDataException(){
        super();
    }
}
