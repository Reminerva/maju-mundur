package com.majumundur.majumundur.service.serviceimpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.majumundur.majumundur.constant.DbBash;
import com.majumundur.majumundur.entity.AppUser;
import com.majumundur.majumundur.repository.AppUserRepository;
import com.majumundur.majumundur.service.AppUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;

    @Override
    public AppUser getById(String id) {
        Optional<AppUser> customer = appUserRepository.findById(id);
        if (customer.isEmpty()) throw new RuntimeException(DbBash.USER_NOT_FOUND);
        return customer.get();
    }

}
