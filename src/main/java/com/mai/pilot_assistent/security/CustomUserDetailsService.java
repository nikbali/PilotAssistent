package com.mai.pilot_assistent.security;

import com.mai.pilot_assistent.model.User;
import com.mai.pilot_assistent.repository.UserRepository;
import exception.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        try {
            return UserPrincipal.create(userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail));
        } catch (Exception e) {
            throw new UsernameNotFoundException("Пользователь не найден по email или username : " + usernameOrEmail);
        }
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}