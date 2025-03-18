package com.george.countandmovement.controller;

import com.george.countandmovement.model.dto.TransactionRequestDTO;
import com.george.countandmovement.model.dto.TransactionResponseDTO;
import com.george.countandmovement.service.ITransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/transactions")
public class TransactionController {
    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return ResponseEntity.ok(transactionService.saveTransaction(transactionRequestDTO));
    }

    @GetMapping("/reports")
    public ResponseEntity<List<TransactionResponseDTO>> reportTransaction(@RequestParam("date") String dateRange,
                                                                          @RequestParam("customer") Integer customerId) {
        String[] dates = dateRange.split(",");

        LocalDateTime startDate = LocalDate.parse(dates[0]).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(dates[1]).atTime(23, 59, 59, 999999999);

        return ResponseEntity.ok(transactionService.findByAccount_AccountIdAndDateBetween(customerId,startDate, endDate));
    }



}
