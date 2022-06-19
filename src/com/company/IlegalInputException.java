package com.company;

public class IlegalInputException extends Exception {
    public IlegalInputException(String description) {
        super(description);
    }
}
