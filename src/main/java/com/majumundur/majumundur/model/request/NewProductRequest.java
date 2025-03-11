package com.majumundur.majumundur.model.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class NewProductRequest {

    @NotBlank(message = "Name is required ")
    private String productName;
    @Min(value = 0, message = "price cannot be negative")
    private String price;
    @Min(value = 0, message = "stock cannot be negative")
    private Integer stock;
    private Boolean isPriceActive;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate priceDate;
    private String merchant;

}
