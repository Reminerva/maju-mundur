package com.majumundur.majumundur.model.request;

import com.majumundur.majumundur.entity.AppUser;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewMerchantRequest {

    @NotBlank(message = "Merchant name is required ")
    private String merchantName;
    private String phone;
    @NotBlank(message = "Merchant location is required ")
    private String location;
    @Schema(hidden = true)
    private AppUser user;
}
