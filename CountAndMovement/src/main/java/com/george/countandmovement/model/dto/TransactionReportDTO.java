package com.george.countandmovement.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TransactionReportDTO {
    private Integer customerId;
    private List<TransactionResponseDTO> transactions;
    private BigDecimal totalBalance;
}
