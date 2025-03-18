package com.george.countandmovement.service;

import com.george.countandmovement.model.dto.TransactionReportDTO;
import com.george.countandmovement.model.dto.TransactionRequestDTO;
import com.george.countandmovement.model.dto.TransactionResponseDTO;
import com.george.countandmovement.model.entity.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ITransactionService {

    public TransactionResponseDTO saveTransaction(TransactionRequestDTO transactionRequestDTO);
    public TransactionReportDTO  findByCustomerAndDateRange(Integer customerId, LocalDateTime startDate, LocalDateTime endDate);
    public TransactionReportDTO  findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
