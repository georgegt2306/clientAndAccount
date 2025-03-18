package com.george.countandmovement.service.impl;

import com.george.countandmovement.mapper.ITransactionMapper;
import com.george.countandmovement.model.dto.TransactionRequestDTO;
import com.george.countandmovement.model.dto.TransactionResponseDTO;
import com.george.countandmovement.model.entity.Account;
import com.george.countandmovement.model.entity.Transaction;
import com.george.countandmovement.repository.IAccountRepository;
import com.george.countandmovement.repository.ITransactionRepository;
import com.george.countandmovement.service.ITransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final ITransactionMapper transactionMapper;
    private final IAccountRepository accountRepository;

    public TransactionService(ITransactionRepository transactionRepository,
                              ITransactionMapper transactionMapper,
                              IAccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public TransactionResponseDTO saveTransaction(TransactionRequestDTO transactionRequestDTO) {
        Account account = obtenerCuenta(transactionRequestDTO.getId());

        validarFondos(account.getInitialBalance(), transactionRequestDTO.getValue());

        Transaction transaction = construirTransaccion(account, transactionRequestDTO.getValue());
        Transaction savedTransaction = transactionRepository.save(transaction);

        actualizarSaldoCuenta(account, transaction.getSaldo());

        return transactionMapper.toDto(savedTransaction);
    }

    @Override
    public List<TransactionResponseDTO> findByAccount_AccountIdAndDateBetween(Integer accountId, LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByAccount_AccountIdAndDateBetween(accountId, startDate, endDate)
                .stream().map(transaction -> transactionMapper.toDto(transaction))
                .toList();
    }

    private Account obtenerCuenta(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("No se encontró la cuenta con el ID proporcionado."));
    }

    private void validarFondos(BigDecimal saldoActual, BigDecimal montoTransaccion) {
        if (saldoActual.add(montoTransaccion).signum() == -1) {
            throw new IllegalArgumentException("Fondos insuficientes para procesar la transacción.");
        }
    }

    private Transaction construirTransaccion(Account account, BigDecimal transactionAmount) {
        BigDecimal updatedBalance = account.getInitialBalance().add(transactionAmount);

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
