package com.majumundur.majumundur.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.model.request.AuthRequest;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.model.response.RegisterResponse;
import com.majumundur.majumundur.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiBash.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiBash.MERCHANT_SIGNUP)
    public ResponseEntity<CommonResponse<RegisterResponse>> createUserMerchantAccount(@RequestBody AuthRequest authRequest){
        RegisterResponse newUserAccount = authService.registerMerchant(authRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
            .message(ApiBash.SIGNUP_SUCCESS)
            .data(newUserAccount)
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(ApiBash.CUSTOMER_SIGNUP)
    public ResponseEntity<CommonResponse<RegisterResponse>> createUserCustomerAccount(@RequestBody AuthRequest authRequest){
        RegisterResponse newUserAccount = authService.registerCustomer(authRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
            .message(ApiBash.SIGNUP_SUCCESS)
            .data(newUserAccount)
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
