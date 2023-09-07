package com.membermanagement.Member.Management.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public @AllArgsConstructor class ApiResponse<T> {

    private boolean success;
    private String message;
    private T payload;
    private List<String> errors;



    public ApiResponse(boolean success, String message, T payload) {
        super();
        this.success = success;
        this.message = message;
        this.payload = payload;
    }


    public ApiResponse(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }


}
