package ru.javalab.rabbitmq.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String name;
    private String surname;
    private String pass_number;
    private String age;
    private String date;
    private String email;
}
