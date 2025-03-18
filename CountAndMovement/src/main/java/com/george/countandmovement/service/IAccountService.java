package com.george.countandmovement.service;

import com.george.countandmovement.model.dto.AccountRequestDTO;
import com.george.countandmovement.model.dto.AccountResponseDTO;
import com.george.countandmovement.model.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccountService {

    public AccountResponseDTO getAccountById(Long id);

    public List<AccountResponseDTO> getAllAccounts();

    public AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO);

    public AccountResponseDTO updateAccount(AccountRequestDTO accountRequestDTO);

    public void deleteAccount(Long id);
}
