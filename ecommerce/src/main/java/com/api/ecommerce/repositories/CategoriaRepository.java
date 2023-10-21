package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ecommerce.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
