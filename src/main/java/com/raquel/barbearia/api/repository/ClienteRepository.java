package com.raquel.barbearia.api.repository;

import com.raquel.barbearia.api.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}