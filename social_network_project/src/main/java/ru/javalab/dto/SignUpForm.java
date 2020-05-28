package ru.javalab.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {
    @Email(message = "{errors.incorrect.email}")
    private String email;

    @NotNull(message = "{errors.incorrect.name}")
    private String name;

    @Size(min = 6, message = "{errors.incorrect.password}")
    private String password;

}
