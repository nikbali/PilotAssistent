package com.mai.pilot_assistent.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private String id;
    private String username;
    private String name;
    private String email;
    private int gender;
    private String birth;
}
