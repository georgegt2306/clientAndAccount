package com.george.practica2.controller;

import com.george.practica2.RabbitMQSender;
import com.george.practica2.model.dto.CustomerIdRequestDTO;
import com.george.practica2.model.dto.CustomerRequestDTO;
import com.george.practica2.model.dto.CustomerResponseDTO;
import com.george.practica2.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {

    private final ICustomerService customerService;
    private final RabbitMQSender rabbitMQSender;

    public CustomerController(ICustomerService customerService, RabbitMQSender rabbitMQSender) {
        this.customerService = customerService;
        this.rabbitMQSender = rabbitMQSender;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCsutomer() {
        return ResponseEntity.ok(customerService.getAllCsutomer());
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO savedCustomer = customerService.save(customerRequestDTO);
        rabbitMQSender.sendCustomer(savedCustomer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PutMapping
    public ResponseEntity<CustomerResponseDTO> update(@Valid @RequestBody CustomerIdRequestDTO customerWithIdRequestDTO) {
        return ResponseEntity.ok(customerService.update(customerWithIdRequestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

}

