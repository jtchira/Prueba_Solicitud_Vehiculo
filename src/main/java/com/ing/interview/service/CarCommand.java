package com.ing.interview.service;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class CarCommand {

    @NonNull
    private final int age;

    private final String color;

    @NonNull
    @NotBlank
    private final String model;

}
