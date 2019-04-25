package com.mai.pilot_assistent.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private  String accessToken;
    private  UserProfile userProfile;
}
