package com.majumundur.majumundur.model.request;

import jakarta.validation.constraints.Min;
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
public class NewTransactionDetailRequest {

    private String productId;

    @Min(value = 0, message = "quantity cannot be negative")
    private Integer qty;
}
