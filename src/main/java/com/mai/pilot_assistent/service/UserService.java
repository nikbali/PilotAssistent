package com.mai.pilot_assistent.service;

import com.mai.pilot_assistent.util.Result;
import com.mai.pilot_assistent.model.User;

import java.util.List;

/**
 * Сервис для работы с пользователями, используется в контроллерах
 */
public interface UserService {

    /**
     * Ищет пользователя по email
     * @param email email
     */
    Result<User> findByEmail(String email);

    /**
     * Ищет пользователя сначала по email, потом по username
     * @param usernameOrEmail строка для поиска
     */
    Result<User> findByUsernameOrEmail(String usernameOrEmail);

    /**
     * Проверяет существует ли пользователь с таким username
     * @param username строка для поиска
     */
    Result<Boolean> existsByUsername(String username);

    /**
     * Проверяет существует ли пользователь с таким email
     * @param email строка для поиска
     */
    Result<Boolean> existsByEmail(String email);


    Result<User> findByUsername(String username);

    Result<User> createUser(User user);

    Result<User> updateUser(User user);
}
