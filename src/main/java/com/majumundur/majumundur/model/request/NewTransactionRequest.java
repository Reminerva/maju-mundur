package com.majumundur.majumundur.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTransactionRequest {

    private String customerId;
    private List<NewTransactionDetailRequest> transactionDetail;
    private Double nominal;

}
