package com.mai.pilot_assistent.service;

import com.mai.pilot_assistent.controller.dto.JwtAuthenticationResponse;
import com.mai.pilot_assistent.model.User;
import com.mai.pilot_assistent.util.Result;

/**
 * Сервис для авторизации и регистрации. Используем в контроллере.
 */
public interface AuthService {

    Result<JwtAuthenticationResponse> doSignIn(String login, String password);

    Result<Void> doSignUp(User user);
}
