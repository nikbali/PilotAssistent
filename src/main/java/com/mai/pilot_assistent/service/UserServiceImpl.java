package com.mai.pilot_assistent.service;

import com.google.common.base.Preconditions;
import com.mai.pilot_assistent.util.Result;
import com.mai.pilot_assistent.model.User;
import com.mai.pilot_assistent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public Result<User> findByEmail(String email) {
        Preconditions.checkArgument(!email.isEmpty());
        return Result.retrieve(() -> userRepository.findByEmail(email));
    }

    @Override
    public Result<User> findByUsernameOrEmail(String username, String email) {
        Preconditions.checkArgument(!email.isEmpty() || !username.isEmpty());
        return Result.retrieve(() -> userRepository.findByUsernameOrEmail(username,email));
    }

    @Override
    public Result<List<User>> findByIdIn(List<String> userIds) {
        Preconditions.checkArgument(userIds != null);
        return Result.retrieve(() -> userRepository.findByIdIn(userIds));
    }

    @Override
    public Result<User> findByUsername(String username) {
        Preconditions.checkArgument(!username.isEmpty());
        return Result.retrieve(() -> userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден!"))
        );
    }

    @Override
    public Result<User> createUser(User user) {
        Preconditions.checkArgument(user != null);
        return Result.retrieve(() -> userRepository.save(user));
    }

    @Override
    public Result<User> updateUser(User user) {
        Preconditions.checkArgument(user != null);
        return Result.retrieve(() -> userRepository.save(user));
    }
}
