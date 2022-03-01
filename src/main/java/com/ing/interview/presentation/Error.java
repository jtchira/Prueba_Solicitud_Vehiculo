package com.ing.interview.presentation;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Error {

    private int code;
    private String message;
    private String description;

}
