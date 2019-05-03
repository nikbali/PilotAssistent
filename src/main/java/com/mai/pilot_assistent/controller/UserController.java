package com.mai.pilot_assistent.controller;


import com.mai.pilot_assistent.controller.dto.UserProfile;
import com.mai.pilot_assistent.controller.dto.base.ErrorResponse;
import com.mai.pilot_assistent.security.CurrentUser;
import com.mai.pilot_assistent.security.UserPrincipal;
import com.mai.pilot_assistent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUser(@PathVariable(value = "username") String username) {
        return userService.findByUsername(username)
                .fold(
                        (user) -> ResponseEntity.ok(UserProfile.fromUser(user)),

                        (failure) -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(ErrorResponse.builder()
                                        .errorText(failure.getMessage())
                                        .build())
                );
    }

    @GetMapping("/user/me")
    public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return userService.findByUsername(currentUser.getUsername())
                .fold(
                        (user) -> ResponseEntity.ok(UserProfile.fromUser(user)),

                        (failure) -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(ErrorResponse.builder()
                                        .errorText(failure.getMessage())
                                        .build())
                );
    }
}
