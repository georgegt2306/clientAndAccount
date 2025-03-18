package com.george.practica2.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Person{
    @Column(nullable = false)
    private String name;
    private String gender;
    private int age;
    @Column(nullable = false, unique = true)
    private String identification;
    private String address;
    private String phone;
}
