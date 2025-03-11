package com.majumundur.majumundur.model.request;

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
public class SearchProductRequest {
    
    private String productName;
    private Integer price;
    private Boolean isPriceActive;
    private String priceDate;

}
