package com.mai.pilot_assistent.util.converters;

import com.mai.pilot_assistent.controller.dto.UserProfile;
import com.mai.pilot_assistent.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

final public class ConvertUtils {

    public static UserProfile toUserProfile(User user) {
        UserProfile.UserProfileBuilder builder = UserProfile.builder();
        if (user.getBirth() != null) {
            builder.birth(toStringDate(user.getBirth()));
        }
        builder.id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .gender(user.getGender());
        return builder.build();

    }

    public static Date toDate(String dateJson) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(dateJson);
        }catch(ParseException ex){
            throw new RuntimeException(String.format("Невалидный формат даты: %s", dateJson));
        }
    }

    public static String toStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
