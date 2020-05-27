package ru.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javalab.dto.SignUpDto;
import ru.javalab.models.User;
import ru.javalab.repositories.UserRepository;
import ru.javalab.util.JwtToken;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void login(SignUpDto signUpDto) {
        Optional<User> user = userRepository.findUserByEmail(signUpDto.getEmail());
        if(user.isPresent()){
            if(passwordEncoder.matches(signUpDto.getPassword(), user.get().getPassword())){
                String token = JwtToken.createToken(String.valueOf(user.get().getId()));
                signUpDto.setToken(token);
            }
        }
    }

    @Override
    public void register(SignUpDto signUpDto) {
        if(!userRepository.findUserByEmail(signUpDto.getEmail()).isPresent()) {
            User user = User.builder()
                    .email(signUpDto.getEmail())
                    .password(signUpDto.getPassword())
                    .name(signUpDto.getName())
                    .build();

            userRepository.save(user);
        }
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.find(id);
    }

    @Override
    public boolean checkCookie(String token) {
        Long userId = JwtToken.getIdFromJwt(token);
        Optional<User> user = userRepository.find(userId);

        return user.isPresent();
    }
}
