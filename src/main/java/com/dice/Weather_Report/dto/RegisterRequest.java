package com.dice.Weather_Report.dto;

import javax.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String userName,
        String email,
        String mobileNumber,
        @NotBlank String password)
{

    // concise way to encapsulate immutable class
    // hashCode()  equals() toString() auto generated
    // getters generated   not setters
}
