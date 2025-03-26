package com.george.countandmovement.service.impl;

import com.george.countandmovement.mapper.ITransactionMapper;
import com.george.countandmovement.model.dto.TransactionReportDTO;
import com.george.countandmovement.model.dto.TransactionRequestDTO;
import com.george.countandmovement.model.dto.TransactionResponseDTO;
import com.george.countandmovement.model.entity.Account;
import com.george.countandmovement.model.entity.Transaction;
import com.george.countandmovement.repository.IAccountRepository;
import com.george.countandmovement.repository.ITransactionRepository;
import com.george.countandmovement.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final ITransactionMapper transactionMapper;
    private final IAccountRepository accountRepository;

    @Autowired
    public TransactionService(ITransactionRepository transactionRepository,
                              ITransactionMapper transactionMapper,
                              IAccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public TransactionResponseDTO saveTransaction(TransactionRequestDTO transactionRequestDTO) {
        Account account = obtenerCuenta(transactionRequestDTO.getAccountNumber());

        validarFondos(account.getInitialBalance(), transactionRequestDTO.getValue(), transactionRequestDTO.getMovementType());

        Transaction transaction = construirTransaccion(account, transactionRequestDTO.getValue(), transactionRequestDTO.getMovementType());
        Transaction savedTransaction = transactionRepository.save(transaction);

        actualizarSaldoCuenta(account, transaction.getSaldo());

        return transactionMapper.toDto(savedTransaction);
    }

    public TransactionReportDTO findByCustomerAndDateRange(Integer customerId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Transaction> transactions = transactionRepository.findByAccount_AccountIdAndDateBetween(customerId, startDate, endDate);

        List<TransactionResponseDTO> transactionDTOs = transactions.stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());

        BigDecimal totalBalance = transactionDTOs.stream()
                .map(TransactionResponseDTO::getSaldo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        TransactionReportDTO report = new TransactionReportDTO();
        report.setCustomerId(customerId);
        report.setTransactions(transactionDTOs);
        report.setTotalBalance(totalBalance);

        return report;
    }

    public TransactionReportDTO findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Transaction> transactions = transactionRepository.findByDateBetween(startDate, endDate);

        List<TransactionResponseDTO> transactionDTOs = transactions.stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());

        BigDecimal totalBalance = transactionDTOs.stream()
                .map(TransactionResponseDTO::getSaldo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        TransactionReportDTO report = new TransactionReportDTO();
        report.setTransactions(transactionDTOs);
        report.setTotalBalance(totalBalance);

        return report;
    }
    public Account obtenerCuenta(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("No se encontró la cuenta con el número proporcionado."));
    }

    private void validarFondos(BigDecimal saldoActual, BigDecimal montoTransaccion, String movementType) {
        if ("WITHDRAW".equalsIgnoreCase(movementType) && saldoActual.compareTo(montoTransaccion) < 0) {
            throw new IllegalArgumentException("Fondos insuficientes para procesar el retiro.");
        }
    }

    private Transaction construirTransaccion(Account account, BigDecimal transactionAmount, String movementType) {
        BigDecimal updatedBalance = "WITHDRAW".equalsIgnoreCase(movementType)
                ? account.getInitialBalance().subtract(transactionAmount)
                : account.getInitialBalance().add(transactionAmount);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setValue(transactionAmount);
        transaction.setSaldo(updatedBalance);
        transaction.setDate(new Date());

        return transaction;
    }

    private void actualizarSaldoCuenta(Account account, BigDecimal nuevoSaldo) {
        account.setInitialBalance(nuevoSaldo);
        accountRepository.save(account);
    }


}
