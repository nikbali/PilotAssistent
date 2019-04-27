package com.mai.pilot_assistent.controller;


import com.mai.pilot_assistent.controller.dto.*;
import com.mai.pilot_assistent.controller.dto.base.ErrorResponse;
import com.mai.pilot_assistent.controller.dto.base.SuccessResponse;
import com.mai.pilot_assistent.model.Role;
import com.mai.pilot_assistent.model.User;
import com.mai.pilot_assistent.repository.UserRepository;
import com.mai.pilot_assistent.security.JwtTokenProvider;
import com.mai.pilot_assistent.util.converters.ConvertUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsernameOrEmail(),
                            loginRequest.getPassword()
                    )
            );
            User user = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail());
            String jwt = tokenProvider.generateToken(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, ConvertUtils.toUserProfile(user)));

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ErrorResponse.builder()
                            .errorText("Ошибка авторизации! Неправильный логин или пароль")
                            .build());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ErrorResponse("Пользователь с таким никнеймом уже существует!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ErrorResponse("Пользователь с таким email адресом уже существует!"),
                    HttpStatus.BAD_REQUEST);
        }

        try {

            User user = User.builder()
                .id(ObjectId.get().toString())
                .name(signUpRequest.getName())
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .gender(signUpRequest.getGender())
                .birth(ConvertUtils.toDate(signUpRequest.getBirth()))
                .build();

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAuthorities(Collections.singleton(Role.USER));
            userRepository.save(user);
            return ResponseEntity.ok(new SuccessResponse(true, "Пользователь успешно зарегистрирован"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex.getMessage()));
        }
    }
}
