package com.george.countandmovement.service;

import com.george.countandmovement.model.dto.TransactionRequestDTO;
import com.george.countandmovement.model.dto.TransactionResponseDTO;
import com.george.countandmovement.model.entity.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ITransactionService {

    public TransactionResponseDTO saveTransaction(TransactionRequestDTO transactionRequestDTO);
    public List<TransactionResponseDTO> findByAccount_AccountIdAndDateBetween(Integer accountId, LocalDateTime startDate, LocalDateTime endDate);
}
