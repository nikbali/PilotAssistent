package com.mai.pilot_assistent.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private int gender;
    private Date birth;
    private Set<Role> authorities;
    private String username;
    private String password;
}
