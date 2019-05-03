package com.mai.pilot_assistent.service.impl;

import com.google.common.base.Preconditions;
import com.mai.pilot_assistent.service.UserService;
import com.mai.pilot_assistent.util.Result;
import com.mai.pilot_assistent.model.User;
import com.mai.pilot_assistent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Result<User> findByEmail(String email) {
        return Result.retrieve(() ->{
            Preconditions.checkArgument(!email.isEmpty());
            return userRepository.findByEmail(email);
        });
    }

    @Override
    public Result<User> findByUsernameOrEmail(String usernameOrEmail) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(!usernameOrEmail.isEmpty());
            return userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
        });
    }

    @Override
    public Result<Boolean> existsByUsername(String username) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(!username.isEmpty());
            return userRepository.existsByUsername(username);
        });
    }

    @Override
    public Result<Boolean> existsByEmail(String email) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(!email.isEmpty());
            return userRepository.existsByEmail(email);
        });
    }

    @Override
    public Result<User> findByUsername(String username) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(!username.isEmpty());
            return userRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("Пользователь не найден!"));
                }
        );
    }

    @Override
    public Result<User> createUser(User user) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(user != null);
            return userRepository.save(user);
        });
    }

    @Override
    public Result<User> updateUser(User user) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(user != null);
            return userRepository.save(user);
        });
    }

    @Override
    public Result<User> getCurrentUser() {
        return null;
    }
}
