package com.majumundur.majumundur.service.serviceimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.majumundur.majumundur.constant.DbBash;
import com.majumundur.majumundur.entity.Customer;
import com.majumundur.majumundur.model.request.NewCustomerRequest;
import com.majumundur.majumundur.repository.CustomerRepository;
import com.majumundur.majumundur.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(NewCustomerRequest customerRequest) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate parsedDate = LocalDate.parse(customerRequest.getDateOfBirth(), formatter);

        Customer customer = Customer.builder()
            .firstName(customerRequest.getFirstName())
            .lastName(customerRequest.getLastName())
            .dateOfBirth(parsedDate)
            .phone(customerRequest.getPhone())
            .status(customerRequest.getStatus())
            .user(customerRequest.getUser())
            .transaction(new ArrayList<>())
            .build();

        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) throw new RuntimeException(DbBash.CUSTOMER_NOT_FOUND);
        return customer.get();
    }

    @Override
    public Customer update(Customer customer) {
        getById(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void delete(String id) {
        Customer customer = getById(id);
        customerRepository.delete(customer);
    }
}
