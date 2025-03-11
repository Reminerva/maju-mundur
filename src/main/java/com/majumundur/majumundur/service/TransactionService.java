package com.majumundur.majumundur.service;

import java.util.List;

import com.majumundur.majumundur.entity.Transaction;
import com.majumundur.majumundur.model.request.NewTransactionRequest;

public interface TransactionService {
    Transaction create(NewTransactionRequest loanTypeRequest);
    Transaction getById(String id);
    List<Transaction> getAll();
    Transaction update(Transaction loanType);
    void delete(String id);
}
