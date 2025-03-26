package com.george.countandmovement.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionRequestDTO {
    private Long id;
    private Date date;
    private String movementType;
    private BigDecimal value;
    private BigDecimal saldo;
    private String accountNumber;

}