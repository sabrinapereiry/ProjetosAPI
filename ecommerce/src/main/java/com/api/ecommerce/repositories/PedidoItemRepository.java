package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ecommerce.entities.PedidoItem;

public interface PedidoItemRepository extends JpaRepository<PedidoItem,Long>{

}
