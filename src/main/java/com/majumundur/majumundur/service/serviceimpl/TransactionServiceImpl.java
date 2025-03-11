package com.majumundur.majumundur.service.serviceimpl;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.majumundur.majumundur.constant.DbBash;
import com.majumundur.majumundur.constant.PaymentStatus;
import com.majumundur.majumundur.entity.AppUser;
import com.majumundur.majumundur.entity.Customer;
import com.majumundur.majumundur.entity.Product;
import com.majumundur.majumundur.entity.Transaction;
import com.majumundur.majumundur.entity.TransactionDetail;
import com.majumundur.majumundur.model.request.NewTransactionDetailRequest;
import com.majumundur.majumundur.model.request.NewTransactionRequest;
import com.majumundur.majumundur.repository.TransactionRepository;
import com.majumundur.majumundur.service.AppUserService;
import com.majumundur.majumundur.service.CustomerService;
import com.majumundur.majumundur.service.ProductService;
import com.majumundur.majumundur.service.TransactionDetailService;
import com.majumundur.majumundur.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDetailService transactionDetailService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final AppUserService appUserService;

    @Override
    public Transaction create(NewTransactionRequest transactionRequest) {
        
        try {

            Integer total = 0;
            for (NewTransactionDetailRequest detailRequest : transactionRequest.getTransactionDetail()) {
                Product product = productService.getById(detailRequest.getProductId());
                total += product.getPrice()*detailRequest.getQty();
            }

            if (total > transactionRequest.getNominal()) {
                throw new RuntimeException("Nominal uang Tidak Mencukupi");
            }

            Customer customer = customerService.getById(transactionRequest.getCustomerId());
            Transaction trx = Transaction.builder()
                .customer(customer)
                .reward(20)
                .paymentStatus(PaymentStatus.PAID)
                .nominal(transactionRequest.getNominal())
                .transactionDate(LocalDate.now())
                .build();
            transactionRepository.saveAndFlush(trx);

            List<TransactionDetail> trxDetail = new ArrayList<>();
            for (NewTransactionDetailRequest detailRequest : transactionRequest.getTransactionDetail()) {
                Product product = productService.getById(detailRequest.getProductId());
                if (product == null) throw new RuntimeException("Produk tidak ditemukan dengan ID: " + detailRequest.getProductId());

                if (product.getStock() - detailRequest.getQty() < 0) {
                    throw new RuntimeException("Produk " + product.getProductName() + " Sold Out");
                }

                product.setStock(product.getStock() - detailRequest.getQty());

                trxDetail.add(TransactionDetail.builder()
                    .transaction(trx)
                    .product(product)
                    .qty(detailRequest.getQty())
                    .price(Double.valueOf(product.getPrice()))
                    .build());
            }

            transactionDetailService.createBulk(trxDetail);
            trx.setTransactionDetail(trxDetail);

            return transactionRepository.saveAndFlush(trx);
            

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Transaction getById(String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) throw new RuntimeException(DbBash.TRASACTION_NOT_FOUND);
        return transaction.get();
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction update(Transaction transaction) {
        getById(transaction.getId());
        transaction.setUpdatedAt(LocalDate.now());
        return transactionRepository.saveAndFlush(transaction);
    }

    @Override
    public void delete(String id) {
        Transaction transaction = getById(id);
        transactionRepository.delete(transaction);
    }
}
