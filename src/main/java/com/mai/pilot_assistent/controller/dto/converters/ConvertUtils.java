package com.mai.pilot_assistent.controller.dto.converters;

import com.mai.pilot_assistent.controller.dto.UserProfile;
import com.mai.pilot_assistent.model.User;

final public class ConvertUtils {

    public static UserProfile toUserProfile(User user) {
        return UserProfile.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
