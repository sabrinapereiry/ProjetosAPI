package com.api.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.academia.entities.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer>{
	
}
