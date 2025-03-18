package com.george.practica2.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerRequestDTO {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    private String password;

    private boolean status;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull
    @Pattern(regexp = "M|F|m|f", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotNull
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private Integer age;

    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Identification must be exactly 10 digits")
    private String identification;

    @NotNull
    @NotBlank
    private String address;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    private String phone;
}
