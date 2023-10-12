package com.api.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.academia.entities.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Integer>{
	
}
