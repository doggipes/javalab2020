package ru.javalab.hw2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginDto {
    private BigInteger id;
    private String email;
    private String password;
}
