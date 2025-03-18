package com.george.practica2.service.impl;

import com.george.practica2.exception.CustomerNotFoundException;
import com.george.practica2.mapper.ICustomerMapper;
import com.george.practica2.model.dto.CustomerIdRequestDTO;
import com.george.practica2.model.dto.CustomerRequestDTO;
import com.george.practica2.model.dto.CustomerResponseDTO;
import com.george.practica2.model.entity.Customer;
import com.george.practica2.repository.ICustomerRepository;
import com.george.practica2.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor  // Lombok para inyectar dependencias por constructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO getById(Long id) {
        // Usamos findById en lugar de getById para evitar EntityNotFoundException
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer con ID " + id + " no encontrado"));
        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCsutomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .toList();
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.toEntity(customerRequestDTO);
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDTO(saved);
    }

    @Override
    public CustomerResponseDTO update(CustomerIdRequestDTO customerIdRequestDTO) {
        // Primero validamos si existe
        Long id = customerIdRequestDTO.getId();
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("No se puede actualizar. Customer con ID " + id + " no encontrado"));

        // Mapear y guardar
        Customer customerToUpdate = customerMapper.toEntity(customerIdRequestDTO);
        Customer updated = customerRepository.save(customerToUpdate);
        return customerMapper.toDTO(updated);
    }

    @Override
    public void deleteCustomer(Long id) {
        // Validamos si existe antes de borrar
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("No se puede eliminar. Customer con ID " + id + " no encontrado"));
        customerRepository.delete(customer);
    }
}
