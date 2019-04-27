package com.mai.pilot_assistent.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class SignUpRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private int gender;
    private String birth;
}
