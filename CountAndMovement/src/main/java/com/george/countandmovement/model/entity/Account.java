package com.george.countandmovement.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @NotBlank
    @Size(min = 10, max = 20)
    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @NotBlank
    @Column(name = "account_type", nullable = false)
    private String accountType;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "initial_balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal initialBalance;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    @NotNull
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
