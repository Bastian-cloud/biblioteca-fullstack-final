package com.example.auth_api.repository;

import com.example.auth_api.model.UsuarioAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioAuthRepository extends JpaRepository<UsuarioAuth, Long> {

    Optional<UsuarioAuth> findByUsername(String username);
}