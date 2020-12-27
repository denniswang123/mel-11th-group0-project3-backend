package com.hero.services;

import com.hero.dtos.user.UserGetDto;
import com.hero.dtos.user.UserPostDto;
import com.hero.entities.User;
import com.hero.mappers.UserMapper;
import com.hero.repositories.AuthorityRepository;
import com.hero.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private final int USERNAME_MIN_LENGTH = 6;
    private final int PASSWORD_MIN_LENGTH = 8;

    public void checkUsername(String username) {
        if (username == null || username.length() < USERNAME_MIN_LENGTH) {
            throw new RuntimeException("Username cannot be less than " + USERNAME_MIN_LENGTH + " characters");
        }

        if (userRepository.findByUsername(username) != null) { throw new RuntimeException("Username already exists"); }
    }

    public void checkEmail(String email) {
        if (email == null || email.length() == 0) {
            throw new RuntimeException("Please input a valid email address");
        }

        if (userRepository.findByEmail(email) != null) { throw new RuntimeException("This email has been used"); }
    }

    public void checkPassword(String password) {
        if (password == null || password.length() < PASSWORD_MIN_LENGTH) {
            throw new RuntimeException("Password cannot be less than " + PASSWORD_MIN_LENGTH + " characters");
        }
    }

    public UserGetDto addOne(UserPostDto userPostDto) {
        checkUsername(userPostDto.getUsername());
        checkEmail(userPostDto.getEmail());

        String password = userPostDto.getPassword();
        checkPassword(password);

        User user = userMapper.toEntity(userPostDto);

        user.setEncodedPassword(passwordEncoder.encode(password));
        user.setStatus("unverified");
        user.setAuthorities(Set.of(authorityRepository.findByPermission("ROLE_TRAINEE")));

        return userMapper.fromEntity(userRepository.save(user));
    }
}
