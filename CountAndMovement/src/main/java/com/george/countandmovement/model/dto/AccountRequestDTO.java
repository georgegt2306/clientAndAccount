package com.george.countandmovement.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestDTO {
    private Long id;
    @NotBlank(message = "Account number required")
    private String accountNumber;
    @NotBlank(message = "Account type required")
    private String accountType;

    @DecimalMin(value = "0.0", inclusive = true, message = "Must be positive")
    private Long initialBalance;

    @NotNull(message = "Status required")
    private Boolean status;

    @NotNull(message = "Customer ID required")
    private Long customerId;

}
