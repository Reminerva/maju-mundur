package com.majumundur.majumundur.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private Integer price;
    @Min(value = 0, message = "stock cannot be negative")
    private Integer stock;
    private Boolean isPriceActive;
    @NotBlank(message = "Price date is required")
    @Schema(type = "string", example = "15-03-2025")
    private String priceDate;
    private String merchant;

}
