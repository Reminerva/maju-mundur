package com.majumundur.majumundur.service.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.majumundur.majumundur.entity.TransactionDetail;
import com.majumundur.majumundur.repository.TransactionDetailRepository;
import com.majumundur.majumundur.service.TransactionDetailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository loanTransactionDetailRepository;

    @Override
    public List<TransactionDetail> createBulk(List<TransactionDetail> loanTransactionDetails) {
        return loanTransactionDetailRepository.saveAllAndFlush(loanTransactionDetails);
    }
}
