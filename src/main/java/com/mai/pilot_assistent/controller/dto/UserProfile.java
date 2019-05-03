package com.mai.pilot_assistent.controller.dto;


import com.mai.pilot_assistent.model.User;
import com.mai.pilot_assistent.util.converters.ConvertUtils;
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

    public static UserProfile fromUser(User user){
        return UserProfile.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .birth(user.getBirth() != null ? ConvertUtils.toStringDate(user.getBirth()) : null)
                .build();
    }
}
