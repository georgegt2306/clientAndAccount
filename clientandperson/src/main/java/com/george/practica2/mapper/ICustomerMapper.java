package com.george.practica2.mapper;

import com.george.practica2.model.dto.CustomerIdRequestDTO;
import com.george.practica2.model.dto.CustomerRequestDTO;
import com.george.practica2.model.dto.CustomerResponseDTO;
import com.george.practica2.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ICustomerMapper {
    Customer toEntity(CustomerRequestDTO customerRequestDTO);
    Customer toEntity(CustomerIdRequestDTO customerIdRequestDTO);
    @Mapping(source = "id", target = "customerId")
    CustomerResponseDTO toDTO(Customer customer);
}
