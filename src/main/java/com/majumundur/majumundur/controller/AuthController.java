package com.majumundur.majumundur.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.model.request.AuthRequest;
import com.majumundur.majumundur.model.response.AuthResponse;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.model.response.LogoutResponse;
import com.majumundur.majumundur.model.response.RegisterResponse;
import com.majumundur.majumundur.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Auth API", description = "API untuk mengelola authentication (register, login & logout)")
@RequiredArgsConstructor
@RequestMapping(ApiBash.AUTH)
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Registrasi akun merchant", description = "Membuat akun baru untuk merchant.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = ApiBash.SIGNUP_SUCCESS)
    })
    @PostMapping(ApiBash.MERCHANT_SIGNUP)
    public ResponseEntity<CommonResponse<RegisterResponse>> createUserMerchantAccount(
            @RequestBody 
            @Schema(example = "{ \"email\": \"merchant@example.com\", \"password\": \"SecurePassword123\" }")
            AuthRequest authRequest) {
        RegisterResponse newUserAccount = authService.registerMerchant(authRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
            .message(ApiBash.SIGNUP_SUCCESS)
            .data(newUserAccount)
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Registrasi akun customer", description = "Membuat akun baru untuk customer.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = ApiBash.SIGNUP_SUCCESS)
    })
    @PostMapping(ApiBash.CUSTOMER_SIGNUP)
    public ResponseEntity<CommonResponse<RegisterResponse>> createUserCustomerAccount(
            @RequestBody 
            @Schema(example = "{ \"email\": \"customer@example.com\", \"password\": \"SecurePassword123\" }")
            AuthRequest authRequest) {
        RegisterResponse newUserAccount = authService.registerCustomer(authRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
            .message(ApiBash.SIGNUP_SUCCESS)
            .data(newUserAccount)
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Login akun", description = "Melakukan login dengan email dan password.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = ApiBash.LOGIN_SUCCESS)
    })
    @GetMapping(ApiBash.LOGIN)
    public ResponseEntity<CommonResponse<AuthResponse>> loginAdminAccount(
        @RequestParam @Parameter(example = "admin@example.com") String email,
        @RequestParam @Parameter(example = "SecureAdmin123") String password
    ) {
        AuthRequest authRequest = new AuthRequest(email, password);
        AuthResponse userAccount = authService.loginUser(authRequest);
        CommonResponse<AuthResponse> response = CommonResponse.<AuthResponse>builder()
            .message(ApiBash.LOGIN_SUCCESS)
            .data(userAccount)
            .build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @Operation(summary = "Logout akun", description = "Melakukan logout dari sistem.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = ApiBash.LOGOUT_SUCCESS)
    })
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
