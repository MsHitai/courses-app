package ru.trush.courses.exception;

public class DataConflictException extends RuntimeException {

    public DataConflictException(String message) {
        super(message);
    }
}
