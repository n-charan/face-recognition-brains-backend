package com.learning.facedetectionbrains.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {

    private String name;

    private String email;

    private String password;
}
