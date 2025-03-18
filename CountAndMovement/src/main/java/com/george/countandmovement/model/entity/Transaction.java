package com.george.countandmovement.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String movementType;
    private BigDecimal value;
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
