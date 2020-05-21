package ru.javalab.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.javalab.models.entity.User;
import ru.javalab.repositories.UserRepository;

import java.util.Optional;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("tut_bil " + email);
        Optional<User> optionUser = userRepository.findUserByEmail(email);
        System.out.println(optionUser.get().getState());
        if (optionUser.isPresent()) {
            return new UserDetailsImpl(optionUser.get());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
