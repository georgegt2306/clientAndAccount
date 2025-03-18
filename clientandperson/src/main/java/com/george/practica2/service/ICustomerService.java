package com.george.practica2.service;

import com.george.practica2.model.dto.CustomerIdRequestDTO;
import com.george.practica2.model.dto.CustomerRequestDTO;
import com.george.practica2.model.dto.CustomerResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ICustomerService {

    public CustomerResponseDTO getById(Long id);

    public CustomerResponseDTO save(CustomerRequestDTO customer);

    public List<CustomerResponseDTO> getAllCsutomer();

    public CustomerResponseDTO update(CustomerIdRequestDTO customer);

    public void deleteCustomer(Long id);
}
