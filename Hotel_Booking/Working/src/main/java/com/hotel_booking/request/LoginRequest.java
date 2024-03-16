package com.hotel_booking.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Shivam Sahu
 */
@Data
public class LoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}