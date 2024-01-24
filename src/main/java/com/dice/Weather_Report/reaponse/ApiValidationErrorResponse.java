package com.dice.Weather_Report.reaponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiValidationErrorResponse {
    private int status;
    private Map<String, List<String>> message;
    // private String error;
     private LocalDateTime timestamp;
}