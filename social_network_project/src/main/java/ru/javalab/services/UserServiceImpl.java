package ru.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javalab.dto.SignUpDto;
import ru.javalab.models.entity.User;
import ru.javalab.models.entity.VerificationToken;
import ru.javalab.models.enums.Role;
import ru.javalab.models.enums.State;
import ru.javalab.repositories.TokenRepository;
import ru.javalab.repositories.UserRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public VerificationToken register(SignUpDto signUpDto) {
        if(!userRepository.findUserByEmail(signUpDto.getEmail()).isPresent()) {
            User user = User.builder()
                    .email(signUpDto.getEmail())
                    .password(signUpDto.getPassword())
                    .name(signUpDto.getName())
                    .state(State.NOT_CONFIRMED)
                    .role(Role.USER)
                    .build();

            userRepository.save(user);

            return createVerificationToken(user);
        }
        return null;
    }

    @Override
    public VerificationToken createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                                                                .token(token)
                                                                .expiryDate(new Timestamp(System.currentTimeMillis()))
                                                                .user(user)
                                                                .build();

        tokenRepository.save(verificationToken);

        return verificationToken;
    }

    @Override
    @Transactional
    public boolean confirmAccount(String token, String email) {
        Optional<VerificationToken> optionalVerificationToken = tokenRepository.findEntityByToken(token);
        if(optionalVerificationToken.isPresent()){
            if(optionalVerificationToken.get().getUser().getEmail().equals(email)){
                userRepository.updateStateForUser(email, State.CONFIRMED);
                tokenRepository.delete(optionalVerificationToken.get().getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.find(id);
    }
}
