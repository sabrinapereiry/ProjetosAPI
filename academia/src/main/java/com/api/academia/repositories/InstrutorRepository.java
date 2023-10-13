package com.api.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.academia.entities.Instrutor;

public interface InstrutorRepository extends JpaRepository<Instrutor, Integer>{
	
}
