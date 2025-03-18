package com.george.countandmovement.controller;

import com.george.countandmovement.model.dto.AccountRequestDTO;
import com.george.countandmovement.model.dto.AccountResponseDTO;
import com.george.countandmovement.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/accounts")
public class AccountController {
    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.ok(accountService.saveAccount(accountRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) {
        accountRequestDTO.setId(id); // Asegurar que el ID de la cuenta se establezca correctamente
        return ResponseEntity.ok(accountService.updateAccount(accountRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
