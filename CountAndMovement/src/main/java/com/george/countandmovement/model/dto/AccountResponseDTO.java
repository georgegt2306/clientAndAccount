package com.george.countandmovement.model.dto;

import com.george.countandmovement.model.entity.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class AccountResponseDTO {
    private Long accountId;
    private String accountNumber;
    private String accountType;
    private Long initialBalance;
    private Boolean status;
    private Long customerId;
    private List<Transaction> movements;
}
