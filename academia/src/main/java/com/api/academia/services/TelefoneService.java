package com.api.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.academia.entities.Telefone;
import com.api.academia.repositories.TelefoneRepository;

@Service
public class TelefoneService {
	@Autowired
    TelefoneRepository telefoneRepo;

    public List<Telefone> listarTelefones() {
        return telefoneRepo.findAll();
    }

    public Telefone buscarIdTelefone(Integer id) {
        return telefoneRepo.findById(id).orElse(null);
    }

    public Telefone salvarTelefone(Telefone telefone) {
        return telefoneRepo.save(telefone);
    }

    public Telefone atualizarTelefone(Telefone telefone) {
        return telefoneRepo.save(telefone);
    }

    public Boolean deletarTelefone(Telefone telefone) {
        if (telefone == null)
            return false;

        Telefone telefoneExistente = buscarIdTelefone(telefone.getIdTelefone());

        if (telefoneExistente == null)
            return false;

        telefoneRepo.delete(telefone);

        Telefone telefoneExiste = buscarIdTelefone(telefone.getIdTelefone());

        if (telefoneExiste == null)
            return true;

        return false;

    }

}
