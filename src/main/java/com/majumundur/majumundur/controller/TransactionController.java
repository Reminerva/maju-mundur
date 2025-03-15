package com.majumundur.majumundur.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.entity.Transaction;
import com.majumundur.majumundur.model.request.NewTransactionRequest;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.service.TransactionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Transaction API", description =  "API untuk mengelola transaction")
@RequestMapping(ApiBash.TRANSACTION)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<CommonResponse<Transaction>> createTransaction(
        @RequestBody NewTransactionRequest newTransactionRequest
    ){

            NewTransactionRequest transaction = NewTransactionRequest.builder()
                                                            .customerId(newTransactionRequest.getCustomerId())
                                                            .nominal(newTransactionRequest.getNominal())
                                                            .transactionDetail(newTransactionRequest.getTransactionDetail())
                                                            .build();

            Transaction newTransaction = transactionService.create(transaction);
            CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
                                                                    .message(ApiBash.CREATE_TRANSACTION_SUCCESS)
                                                                    .data(newTransaction)
                                                                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<CommonResponse<Transaction>> getTransactionById(@PathVariable String id){

        Transaction transaction = transactionService.getById(id);

        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
                                                                    .message(ApiBash.FOUND_TRANSACTION_SUCCESS)
                                                                    .data(transaction)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Transaction>> updateTransaction(@RequestBody Transaction transaction){

        Transaction updatedTransaction = transactionService.update(transaction);

        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
                                                                    .message(ApiBash.UPDATE_TRANSACTION_SUCCESS)
                                                                    .data(updatedTransaction)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(ApiBash.GET_BY_ID)
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Transaction>> deleteTransaction(@PathVariable String id){
        transactionService.delete(id);

        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
                                                                    .message(ApiBash.DELETE_TRANSACTION_SUCCESS)
                                                                    .data(null)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
