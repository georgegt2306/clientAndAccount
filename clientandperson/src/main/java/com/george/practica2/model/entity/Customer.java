package com.george.practica2.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String password;

    private  Boolean status;


}
