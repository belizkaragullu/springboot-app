package com.projects.Product.exception;

public class RunTimeBusinessException extends RuntimeException {

    private String message;

    public  RunTimeBusinessException(String message){
        super(message);
    }
}
