package com.cursor.library.authentication;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.cursor.library.authentication.UserRole.ADMIN;
import static com.cursor.library.authentication.UserRole.USER;

@Service
@Data
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @PostConstruct
    private void config() {
        List<User> applicationUsers = Lists.newArrayList(
                new User(
                        1L,
                        "anna",
                        passwordEncoder.encode("password"),
                        USER,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                        2L,
                        "linda",
                        passwordEncoder.encode("password"),
                        ADMIN,
                        true,
                        true,
                        true,
                        true
                )
        );

        userRepository.saveAll(applicationUsers);
    }
}
