package com.harsh.JWTLearnings.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/* This class is used for login request  for the transfer of data*/
@Data
public class LoginRequest {

    @NotNull(message = "please enter email")
    private  String email;
    @NotNull(message = "please enter password")
    private String password;
}
