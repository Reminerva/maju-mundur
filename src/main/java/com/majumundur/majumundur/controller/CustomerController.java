package com.majumundur.majumundur.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.entity.AppUser;
import com.majumundur.majumundur.entity.Customer;
import com.majumundur.majumundur.model.request.NewCustomerRequest;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.service.AppUserService;
import com.majumundur.majumundur.service.CustomerService;
import com.majumundur.majumundur.util.DateUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiBash.CUSTOMER)
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Customer API", description =  "API untuk mengelola customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AppUserService appUserService;

    @PostMapping("/{user_id}")
    public ResponseEntity<CommonResponse<Customer>> createCustomer(
        @RequestBody NewCustomerRequest newCustomerRequest,
        @PathVariable String user_id
    ){
        
            AppUser appUser = appUserService.getById(user_id);

            NewCustomerRequest customer = NewCustomerRequest.builder()
                                                            .firstName(newCustomerRequest.getFirstName())
                                                            .lastName(newCustomerRequest.getLastName())
                                                            .dateOfBirth(DateUtil.parseDate(newCustomerRequest.getDateOfBirth().toString()))
                                                            .phone(newCustomerRequest.getPhone())
                                                            .status(newCustomerRequest.getStatus())
                                                            .user(appUser)
                                                            .build();

            Customer newCustomer = customerService.create(customer);
            CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                                                                    .message(ApiBash.CREATE_CUSTOMER_SUCCESS)
                                                                    .data(newCustomer)
                                                                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable String id){

        Customer customer = customerService.getById(id);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                                                                    .message(ApiBash.FOUND_CUSTOMER_SUCCESS)
                                                                    .data(customer)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(@RequestBody Customer customer){

        Customer updatedCustomer = customerService.update(customer);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                                                                    .message(ApiBash.UPDATE_CUSTOMER_SUCCESS)
                                                                    .data(updatedCustomer)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<CommonResponse<Customer>> deleteCustomer(@PathVariable String id){
        customerService.delete(id);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                                                                    .message(ApiBash.DELETE_CUSTOMER_SUCCESS)
                                                                    .data(null)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
