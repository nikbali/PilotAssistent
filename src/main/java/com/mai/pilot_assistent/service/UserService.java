package com.mai.pilot_assistent.service;

import com.mai.pilot_assistent.util.Result;
import com.mai.pilot_assistent.model.User;

import java.util.List;

/**
 * Сервис для работы с пользователями, используется в контроллерах
 */
public interface UserService {

    Result<User> findByEmail(String email);

    Result<User> findByUsernameOrEmail(String username, String email);

    Result<List<User>> findByIdIn(List<String> userIds);

    Result<User> findByUsername(String username);

    Result<User> createUser(User user);

    Result<User> updateUser(User user);
}
