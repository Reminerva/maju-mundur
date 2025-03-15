package com.majumundur.majumundur.service;

import com.majumundur.majumundur.model.request.AuthRequest;
import com.majumundur.majumundur.model.response.AuthResponse;
import com.majumundur.majumundur.model.response.LogoutResponse;
import com.majumundur.majumundur.model.response.RegisterResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    RegisterResponse registerMerchant(AuthRequest authRequest);
    RegisterResponse registerCustomer(AuthRequest authRequest);
    AuthResponse loginUser (AuthRequest authRequest);
    LogoutResponse logoutUser (HttpServletRequest authRequest);
}
