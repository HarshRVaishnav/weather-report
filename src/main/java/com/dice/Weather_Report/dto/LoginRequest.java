package com.dice.Weather_Report.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest
(
        @NotBlank(message = "username may not be blank or null") String userName,
         @NotBlank(message = "username may not be blank or null") String password
)
 {
 }
