package com.majumundur.majumundur.service;

import java.util.List;

import com.majumundur.majumundur.entity.TransactionDetail;

public interface TransactionDetailService {
    List<TransactionDetail> createBulk(List<TransactionDetail> transactionDetails);
}
