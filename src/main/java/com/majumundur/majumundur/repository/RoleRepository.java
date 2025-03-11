package com.majumundur.majumundur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.majumundur.majumundur.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
