package com.example.multa.repository;

import com.example.multa.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultaRepository extends JpaRepository<Multa, Long> {}