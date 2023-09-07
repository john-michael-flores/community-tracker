package com.membermanagement.Member.Management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PeopleNotFoundException extends Exception{
    public PeopleNotFoundException(String message) {
        super(message);
    }
}
