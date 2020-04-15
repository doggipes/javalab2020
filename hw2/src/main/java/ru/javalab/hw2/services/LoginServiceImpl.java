package ru.javalab.hw2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.javalab.hw2.dto.LoginDto;
import ru.javalab.hw2.models.User;
import ru.javalab.hw2.repositories.RegisterReposiroty;

import java.util.Optional;

@Component
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RegisterReposiroty registerReposiroty;

    @Override
    public boolean login(LoginDto loginDto) {
        Optional<User> user = registerReposiroty.findUserByEmail(loginDto.getEmail());

        if(user.isPresent()){
            PasswordEncoder encoder = new BCryptPasswordEncoder();

            if(encoder.matches(loginDto.getPassword(), user.get().getPassword())){
                if(user.get().getIsConfirmed()){
                    return true;
                }
            }
        }
        return false;
    }
}
