package com.majumundur.majumundur.model.request;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.majumundur.majumundur.entity.AppUser;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCustomerRequest {

    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    private String phone;
    private String status;
    private AppUser user;
}

