package com.mai.pilot_assistent.service.impl;

import com.google.common.base.Preconditions;
import com.mai.pilot_assistent.controller.dto.JwtAuthenticationResponse;
import com.mai.pilot_assistent.model.Role;
import com.mai.pilot_assistent.model.User;
import com.mai.pilot_assistent.security.JwtTokenProvider;
import com.mai.pilot_assistent.service.AuthService;
import com.mai.pilot_assistent.service.UserService;
import com.mai.pilot_assistent.util.Result;
import com.mai.pilot_assistent.util.converters.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Result<JwtAuthenticationResponse> doSignIn(String login, String password) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(!login.isEmpty() && !password.isEmpty());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new JwtAuthenticationResponse(tokenProvider.generateToken(authentication),
                    ConvertUtils.toUserProfile(userService.findByUsernameOrEmail(login)
                            .getValue()));
        });
    }

    @Override
    public Result<Void> doSignUp(User user) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(user != null);
            Preconditions.checkArgument(!user.getUsername().isEmpty(), "Поле Username должно быть заполнено!");
            Preconditions.checkArgument(!user.getEmail().isEmpty(), "Поле Email должно быть заполнено!");
            Preconditions.checkArgument(!user.getName().isEmpty(), "Имя должно быть заполнено!");
            Preconditions.checkArgument(!user.getPassword().isEmpty(), "Пароль должен быть заполнен!");
            if (userService.existsByUsername(user.getUsername()).getValue()) {
                throw new RuntimeException("Пользователь с таким username уже существует!");
            }
            if (userService.existsByEmail(user.getEmail()).getValue()) {
                throw new RuntimeException("Пользователь с таким email`ом уже существует!");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAuthorities(Collections.singleton(Role.USER));
            userService.createUser(user);
            return null;
        });
    }
}
