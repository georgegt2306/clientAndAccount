package com.george.countandmovement.service.impl;

import com.george.countandmovement.exception.AccountNotFoundException;
import com.george.countandmovement.mapper.IAccountMapper;
import com.george.countandmovement.model.dto.AccountRequestDTO;
import com.george.countandmovement.model.dto.AccountResponseDTO;
import com.george.countandmovement.repository.IAccountRepository;
import com.george.countandmovement.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;
    private final IAccountMapper accountMapper;

    public AccountService(IAccountRepository accountRepository, IAccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new AccountNotFoundException("Cuenta no encontrada."));
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Override
    public AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO) {
        return mapAndSave(accountRequestDTO);
    }

    @Override
    public AccountResponseDTO updateAccount(AccountRequestDTO accountRequestDTO) {
        return accountRepository.findById(accountRequestDTO.getId())
                .map(existingAccount -> mapAndSave(accountRequestDTO))
                .orElseThrow(() -> new AccountNotFoundException("Cuenta no encontrada."));
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findById(id)
                .ifPresentOrElse(
                        accountRepository::delete,
                        () -> { throw new AccountNotFoundException("Cuenta no encontrada."); }
                );
    }

    private AccountResponseDTO mapAndSave(AccountRequestDTO accountRequestDTO) {
        return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(accountRequestDTO)));
    }
}
