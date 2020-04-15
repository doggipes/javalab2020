package ru.javalab.hw2.services;

import ru.javalab.hw2.dto.LoginDto;

public interface LoginService {
    boolean login(LoginDto loginDto);
}
