package com.george.countandmovement.model.dto;

import com.george.countandmovement.model.entity.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionResponseDTO {

    private Long id;
    private Date date;
    private String movementType;
    private BigDecimal value;
    private BigDecimal saldo;
    private Long accountId;  // Id de la cuenta
    private String accountNumber; // Número de la cuenta
    private String accountType;   // Tipo de cuenta
    private Long customerId; // ID del cliente asociado a la cuenta

}
