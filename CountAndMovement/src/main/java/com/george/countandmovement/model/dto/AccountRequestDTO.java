package com.george.countandmovement.model.dto;

import lombok.Data;

@Data
public class AccountRequestDTO {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Long initialBalance;
    private Boolean status;
    private Long customerId;

}
