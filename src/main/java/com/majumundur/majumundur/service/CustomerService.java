package com.majumundur.majumundur.service;

import com.majumundur.majumundur.entity.Customer;
import com.majumundur.majumundur.model.request.NewCustomerRequest;
// import com.majumundur.majumundur.model.request.SearchCustomerRequest;

public interface CustomerService {
    Customer create(NewCustomerRequest customerRequest);
    Customer getById(String id);
    // List<Customer> getAll(SearchCustomerRequest request);
    Customer update(Customer customer);
    void delete(String id);
}
