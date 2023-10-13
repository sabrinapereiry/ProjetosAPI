package com.api.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.academia.entities.Instrutor;
import com.api.academia.repositories.InstrutorRepository;

@Service
public class InstrutorService {
	@Autowired
    InstrutorRepository instrutorRepo;

    public List<Instrutor> listarInstrutores() {
        return instrutorRepo.findAll();
    }

    public Instrutor buscarIdInstrutor(Integer id) {
        return instrutorRepo.findById(id).orElse(null);
    }

    public Instrutor salvarInstrutor(Instrutor instrutor) {
        return instrutorRepo.save(instrutor);
    }

    public Instrutor atualizarInstrutor(Instrutor instrutor) {
        return instrutorRepo.save(instrutor);
    }

    public Boolean deletarInstrutor(Instrutor instrutor) {
        if (instrutor == null)
            return false;

        Instrutor instrutorExistente = buscarIdInstrutor(instrutor.getIdInstrutor());

        if (instrutorExistente == null)
            return false;

        instrutorRepo.delete(instrutor);

        Instrutor instrutorExiste = buscarIdInstrutor(instrutor.getIdInstrutor());

        if (instrutorExiste == null)
            return true;

        return false;

    }

}
