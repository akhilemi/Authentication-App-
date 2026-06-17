package com.akhilprog.authapp.exceptions;

public class ResourceNotFounDException extends RuntimeException{
    public ResourceNotFounDException(String message){
        super(message);
    }

    public ResourceNotFounDException(){
        super("resource Not Found !!");
    }
}
