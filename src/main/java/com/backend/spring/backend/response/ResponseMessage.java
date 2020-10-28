package com.backend.spring.backend.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
@Data
public class ResponseMessage<T> {
    private boolean success;
    private T object;
    private String message;
}

