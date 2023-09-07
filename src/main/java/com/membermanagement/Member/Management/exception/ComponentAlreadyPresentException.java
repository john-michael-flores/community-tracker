package com.membermanagement.Member.Management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ComponentAlreadyPresentException extends Exception{
    public ComponentAlreadyPresentException(String message) {
        super(message);
    }
}
