package com.majumundur.majumundur.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.majumundur.majumundur.constant.Constant;
import com.majumundur.majumundur.constant.ERole;
import com.majumundur.majumundur.entity.AppUser;
import com.majumundur.majumundur.entity.Role;
import com.majumundur.majumundur.model.request.AuthRequest;
import com.majumundur.majumundur.model.response.AuthResponse;
import com.majumundur.majumundur.model.response.LogoutResponse;
import com.majumundur.majumundur.model.response.RegisterResponse;
import com.majumundur.majumundur.repository.AppUserRepository;
import com.majumundur.majumundur.repository.RoleRepository;
import com.majumundur.majumundur.security.JwtAuthenticationFilter;
import com.majumundur.majumundur.security.JwtTokenProvider;
import com.majumundur.majumundur.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTokenBlackListService redisTokenBlackListService;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse registerMerchant(AuthRequest authRequest) {
        try {
            Role role = Role.builder()
                            .role(ERole.ROLE_MERCHANT)
                            .build();

            List<Role> userRole = new ArrayList<>();
            userRole.add(role);

            roleRepository.saveAllAndFlush(userRole);

            AppUser userAccount = AppUser.builder()
                .email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .roles(userRole)
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .build();

            appUserRepository.saveAndFlush(userAccount);

            return toRegisterResponse(userAccount);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constant.USERNAME_DUPLICATE);
        }
    }

    @Override
    public RegisterResponse registerCustomer(AuthRequest authRequest) {
        try {
            Role role = Role.builder()
                            .role(ERole.ROLE_CUSTOMER)
                            .build();

            List<Role> userRole = new ArrayList<>();
            userRole.add(role);

            roleRepository.saveAllAndFlush(userRole);

            AppUser userAccount = AppUser.builder()
                .email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .roles(userRole)
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .build();

            appUserRepository.saveAndFlush(userAccount);

            return toRegisterResponse(userAccount);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constant.USERNAME_DUPLICATE);
        }
    }
    @Override
    public AuthResponse loginUser(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getAuthorities().toString());

        return toAuthResponse(user, token);
    }


    @Override
    public LogoutResponse logoutUser(HttpServletRequest authRequest) {
        String token =  jwtAuthenticationFilter.extractTokenFromRequest(authRequest);
        
        if (token == null || !jwtTokenProvider.validateToken(token)) {

            return toLogoutResponse(token, "Token is null");
        }

        Long expirationTime = jwtTokenProvider.getExpirationTime(token);
        redisTokenBlackListService.blackListToken(token, expirationTime);

        return toLogoutResponse(token, "This token will be blacklisted");
    }
    
    private LogoutResponse toLogoutResponse(String token, String statusMessage) {
        return LogoutResponse.builder()
                .accessToken(token)
                .statusMessage(statusMessage)
                .build();
    }
    
    private AuthResponse toAuthResponse(User user, String token) {
            return AuthResponse.builder()
                    .accessToken(token)
                    .role(user.getAuthorities().toString())
                .build();
    }


    private RegisterResponse toRegisterResponse(AppUser userAccount) {
        return RegisterResponse.builder()
            .email(userAccount.getEmail())
            .roles(List.of(userAccount.getRoles().get(0).getRole().getDescription()))
            .build();
    }
}
