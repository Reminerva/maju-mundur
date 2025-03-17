package com.majumundur.majumundur.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.entity.AppUser;
import com.majumundur.majumundur.entity.Customer;
import com.majumundur.majumundur.model.request.NewCustomerRequest;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.service.AppUserService;
import com.majumundur.majumundur.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiBash.CUSTOMER)
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Customer API", description = "API untuk mengelola customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AppUserService appUserService;

    @Operation(summary = "Buat customer baru", description = "Menambahkan customer baru berdasarkan user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = ApiBash.CREATE_CUSTOMER_SUCCESS)
    })
    @PostMapping("/{user_id}")
    public ResponseEntity<CommonResponse<Customer>> createCustomer(
        @RequestBody @Schema(example = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"dateOfBirth\": \"15-03-2002\", \"phone\": \"08123456789\", \"status\": \"Active\" }")
        NewCustomerRequest newCustomerRequest,
        @PathVariable @Parameter(example = "123e4567-e89b-12d3-a456-426614174000") String user_id
    ){
        AppUser appUser = appUserService.getById(user_id);
        NewCustomerRequest customer = NewCustomerRequest.builder()
                                                        .firstName(newCustomerRequest.getFirstName())
                                                        .lastName(newCustomerRequest.getLastName())
                                                        .dateOfBirth(newCustomerRequest.getDateOfBirth())
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

    @Operation(summary = "Dapatkan customer berdasarkan ID", description = "Mengambil data customer berdasarkan ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = ApiBash.FOUND_CUSTOMER_SUCCESS)
    })
    @GetMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable @Parameter(example = "123e4567-e89b-12d3-a456-426614174000") String id){
        Customer customer = customerService.getById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                                                                    .message(ApiBash.FOUND_CUSTOMER_SUCCESS)
                                                                    .data(customer)
                                                                    .build();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @Operation(summary = "Update customer", description = "Memperbarui data customer berdasarkan ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = ApiBash.UPDATE_CUSTOMER_SUCCESS)
    })
    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(
        @RequestBody @Schema(example = "{ \"id\": \"123e4567-e89b-12d3-a456-426614174000\", \"firstName\": \"John\", \"lastName\": \"Smith\", \"phone\": \"08123456789\", \"status\": \"Inactive\" }")
        Customer customer){
        Customer updatedCustomer = customerService.update(customer);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                                                                    .message(ApiBash.UPDATE_CUSTOMER_SUCCESS)
                                                                    .data(updatedCustomer)
                                                                    .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Hapus customer", description = "Menghapus customer berdasarkan ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = ApiBash.DELETE_CUSTOMER_SUCCESS)
    })
    @DeleteMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<CommonResponse<Customer>> deleteCustomer(@PathVariable @Parameter(example = "123e4567-e89b-12d3-a456-426614174000") String id){
        customerService.delete(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                                                                    .message(ApiBash.DELETE_CUSTOMER_SUCCESS)
                                                                    .data(null)
                                                                    .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
