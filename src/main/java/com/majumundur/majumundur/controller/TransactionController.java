package com.majumundur.majumundur.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.entity.Transaction;
import com.majumundur.majumundur.model.request.NewTransactionRequest;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Transaction API", description = "API untuk mengelola transaction")
@RequestMapping(ApiBash.TRANSACTION)
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(
        summary = "Create a new transaction", 
        description = "Endpoint untuk membuat transaksi baru",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = ApiBash.CREATE_TRANSACTION_SUCCESS
            )
        })
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

    @Operation(
        summary = "Get transaction by ID", 
        description = "Mengambil transaksi berdasarkan ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = ApiBash.FOUND_TRANSACTION_SUCCESS
            )
        })
    @GetMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<CommonResponse<Transaction>> getTransactionById(
        @Parameter(description = "ID transaksi yang ingin dicari") @PathVariable String id
    ){
        Transaction transaction = transactionService.getById(id);

        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
            .message(ApiBash.FOUND_TRANSACTION_SUCCESS)
            .data(transaction)
            .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
        summary = "Update a transaction", 
        description = "Memperbarui data transaksi",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = ApiBash.UPDATE_TRANSACTION_SUCCESS
            )
        })
    @PutMapping
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Transaction>> updateTransaction(
        @RequestBody Transaction transaction
    ){
        Transaction updatedTransaction = transactionService.update(transaction);

        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
            .message(ApiBash.UPDATE_TRANSACTION_SUCCESS)
            .data(updatedTransaction)
            .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
        summary = "Delete a transaction", 
        description = "Menghapus transaksi berdasarkan ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = ApiBash.DELETE_TRANSACTION_SUCCESS
            )
        })
    @DeleteMapping(ApiBash.GET_BY_ID)
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Transaction>> deleteTransaction(
        @Parameter(description = "ID transaksi yang ingin dihapus") @PathVariable String id
    ){
        transactionService.delete(id);

        CommonResponse<Transaction> response = CommonResponse.<Transaction>builder()
            .message(ApiBash.DELETE_TRANSACTION_SUCCESS)
            .data(null)
            .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}