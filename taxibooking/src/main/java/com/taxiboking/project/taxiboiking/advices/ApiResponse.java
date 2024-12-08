package com.taxiboking.project.taxiboiking.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T>{

    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;

    public ApiResponse(T data) {
        this();// this ensure the Time stamp is also included and initialize
        this.data = data;//when successful request it structure the response in the uniform way in data
    }

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this();// this ensure the Time stamp is also included and initialize
        this.error = error;
    }
}
