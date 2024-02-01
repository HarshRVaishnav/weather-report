package com.dice.Weather_Report.dto;

public record RegisterRequest(String name, String email, String mobileNumber, String password)
{

    // concise way to encapsulate immutable class
    // hashCode()  equals() toString() auto generated
    // getters generated   not setters
}
