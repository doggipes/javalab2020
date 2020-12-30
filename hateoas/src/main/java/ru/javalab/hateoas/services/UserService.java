package ru.javalab.hateoas.services;

import ru.javalab.hateoas.models.User;

public interface UserService {
    User changeEmail(Long id, String email);
}
