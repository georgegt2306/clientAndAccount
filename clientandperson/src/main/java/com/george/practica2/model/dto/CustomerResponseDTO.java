package com.george.practica2.model.dto;

import lombok.Data;

@Data
public class CustomerResponseDTO {
    private Long idCustomer;
    private String password;
    private boolean status;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
}
