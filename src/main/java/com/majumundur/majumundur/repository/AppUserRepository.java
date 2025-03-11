package com.majumundur.majumundur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.majumundur.majumundur.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String>{
    Optional<AppUser> findByEmail(String email); 
}
