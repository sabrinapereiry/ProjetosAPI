package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ecommerce.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

}
