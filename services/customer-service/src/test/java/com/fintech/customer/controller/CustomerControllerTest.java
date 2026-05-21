package com.fintech.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.customer.dto.CustomerRequest;
import com.fintech.customer.entity.Customer;
import com.fintech.customer.service.CustomerService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setFullName("Neha Sonar");
        request.setEmail("neha@example.com");
        request.setMobileNumber("9876543210");
        request.setPanNumber("ABCDE1234F");

        Customer response = Customer.builder()
                .id(1L)
                .fullName("Neha Sonar")
                .email("neha@example.com")
                .mobileNumber("9876543210")
                .panNumber("ABCDE1234F")
                .createdAt(LocalDateTime.now())
                .build();

        when(customerService.createCustomer(any(CustomerRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("neha@example.com"));
    }

    @Test
    void shouldGetAllCustomers() throws Exception {
        Customer customer = Customer.builder()
                .id(1L)
                .fullName("Neha Sonar")
                .email("neha@example.com")
                .mobileNumber("9876543210")
                .panNumber("ABCDE1234F")
                .createdAt(LocalDateTime.now())
                .build();

        when(customerService.getAllCustomers()).thenReturn(List.of(customer));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("neha@example.com"));
    }

    @Test
    void shouldGetCustomerById() throws Exception {
        Customer customer = Customer.builder()
                .id(1L)
                .fullName("Neha Sonar")
                .email("neha@example.com")
                .mobileNumber("9876543210")
                .panNumber("ABCDE1234F")
                .createdAt(LocalDateTime.now())
                .build();

        when(customerService.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("neha@example.com"));
    }
}