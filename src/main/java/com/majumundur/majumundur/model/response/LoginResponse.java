package com.majumundur.majumundur.model.response;

import java.util.List;

import com.majumundur.majumundur.entity.Role;

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
public class LoginResponse {
    
    private String email;
    private List<Role> roles;
    private String token;
    
}
