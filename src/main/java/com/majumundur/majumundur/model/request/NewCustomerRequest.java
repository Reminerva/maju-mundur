package com.majumundur.majumundur.model.request;

import com.majumundur.majumundur.entity.AppUser;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCustomerRequest {

    private String firstName;
    private String lastName;

    @Schema(type = "string", example = "15-03-2025")
    private String dateOfBirth;

    private String phone;
    private String status;
    private AppUser user;
}

