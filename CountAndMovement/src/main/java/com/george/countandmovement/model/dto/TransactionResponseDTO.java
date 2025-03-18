package com.george.countandmovement.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionResponseDTO {

    private Long id;
    private Date date;
    private String MovementType;
    private BigDecimal value;
    private BigDecimal saldo;
}
