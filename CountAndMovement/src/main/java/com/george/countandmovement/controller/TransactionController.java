package com.george.countandmovement.controller;

import com.george.countandmovement.model.dto.TransactionReportDTO;
import com.george.countandmovement.model.dto.TransactionRequestDTO;
import com.george.countandmovement.model.dto.TransactionResponseDTO;
import com.george.countandmovement.service.ITransactionService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<TransactionReportDTO> reportTransaction(@RequestParam("date") @NotBlank String dateRange, @RequestParam(value = "customer", required = false) Integer customerId) {

        String[] dates = dateRange.split(",");
        if (dates.length != 2) {
            return ResponseEntity.badRequest().body(null);
        }

        LocalDateTime startDate = LocalDate.parse(dates[0]).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(dates[1]).atTime(23, 59, 59, 999999999);


        // Llamada al servicio para obtener el reporte completo
        TransactionReportDTO report;

        if (customerId != null) {
            report = transactionService.findByCustomerAndDateRange(customerId, startDate, endDate);
        } else {
            report = transactionService.findByDateRange(startDate, endDate);
        }


        return ResponseEntity.ok(report);}



}
