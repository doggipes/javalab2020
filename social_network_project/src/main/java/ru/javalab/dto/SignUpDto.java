package ru.javalab.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpDto {
    private String name;
    private String email;
    private String password;
}

