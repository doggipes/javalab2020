package ru.javalab.hw2.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private BigInteger id;
    private String name;
    private String email;
    private String password;
    private Boolean isConfirmed;
}
