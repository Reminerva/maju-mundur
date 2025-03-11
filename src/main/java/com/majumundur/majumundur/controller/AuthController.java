package com.majumundur.majumundur.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.model.request.AuthRequest;
import com.majumundur.majumundur.model.response.AuthResponse;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.model.response.LogoutResponse;
import com.majumundur.majumundur.model.response.RegisterResponse;
import com.majumundur.majumundur.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping(ApiBash.LOGIN)
    public ResponseEntity<CommonResponse<AuthResponse>> loginAdminAccount(
        @RequestParam String email,
        @RequestParam String password
    ){

    AuthRequest authRequest = new AuthRequest(email, password);
        AuthResponse userAccount = authService.loginUser(authRequest);
        CommonResponse<AuthResponse> response = CommonResponse.<AuthResponse>builder()
            .message(ApiBash.LOGIN_SUCCESS)
            .data(userAccount)
            .build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

        @PostMapping(ApiBash.LOGOUT)
    public ResponseEntity<CommonResponse<LogoutResponse>> logoutAdminAccount(HttpServletRequest authRequest) {
        LogoutResponse userAccount = authService.logoutUser(authRequest);
        CommonResponse<LogoutResponse> response = CommonResponse.<LogoutResponse>builder()
            .message(ApiBash.LOGOUT_SUCCESS)
            .data(userAccount)
            .build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
