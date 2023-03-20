package com.learning.facedetectionbrains.dto;

import lombok.Data;

@Data
public class UserProfileDto {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private Integer entries;

    private String joined;
}
