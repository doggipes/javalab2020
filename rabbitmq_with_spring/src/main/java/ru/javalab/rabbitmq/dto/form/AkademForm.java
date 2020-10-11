package ru.javalab.rabbitmq.dto.form;

import lombok.Data;

@Data
public class AkademForm {
    private String name;
    private String surname;
    private String pass_number;
    private String age;
    private String date;
    private String email;
}
