package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ecommerce.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
