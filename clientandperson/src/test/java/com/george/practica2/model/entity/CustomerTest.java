package com.george.practica2.model.entity;

import com.george.practica2.model.entity.Customer;
import com.george.practica2.repository.ICustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerTest {

    @Autowired
    private ICustomerRepository customerRepository;

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setGender("Male");
        customer.setAge(30);
        customer.setIdentification("123456789");
        customer.setAddress("123 Street");
        customer.setPhone("987654321");
        customer.setPassword("securepassword");
        customer.setStatus(true);

        Customer savedCustomer = customerRepository.save(customer);


        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isNotNull();
        assertThat(savedCustomer.getName()).isEqualTo("John Doe");
    }
}
