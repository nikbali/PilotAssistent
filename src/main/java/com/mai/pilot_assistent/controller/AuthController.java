package com.mai.pilot_assistent.controller;

import com.mai.pilot_assistent.controller.dto.LoginRequest;
import com.mai.pilot_assistent.controller.dto.SignUpRequest;
import com.mai.pilot_assistent.controller.dto.base.ErrorResponse;
import com.mai.pilot_assistent.controller.dto.base.SuccessResponse;
import com.mai.pilot_assistent.service.AuthService;
import com.mai.pilot_assistent.util.converters.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.doSignIn(loginRequest.getUsernameOrEmail(), loginRequest.getPassword())
                .fold(
                        ResponseEntity::ok,
                        (failure) -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(ErrorResponse.builder()
                                        .errorText("Ошибка авторизации! Неправильный логин или пароль")
                                        .build())
                );

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.doSignUp(ConvertUtils.fromSignUpRequestToUser(signUpRequest))
                .fold(
                        (success) -> ResponseEntity.ok(new SuccessResponse(true, "Пользователь успешно зарегистрирован")),
                        (failure) -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(failure.getMessage()))
                );
    }
}
