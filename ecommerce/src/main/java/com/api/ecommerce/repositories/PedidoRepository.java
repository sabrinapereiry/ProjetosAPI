package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ecommerce.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
