package com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.entities.Endereco;
import com.api.ecommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepo;

	public List<Endereco> listarEndereco() {
		return enderecoRepo.findAll();// retorna a lista de endereco.

	}

	public Endereco buscarEnderecoPorId(Long id) { // precisa passar o parametro.
		// return enderecoRepo.findById(id).get();
		// Optional<Endereco> enderecoBanco = .findByenderecoRepoId(id);
		// if (endereco.isPresent()) {
		// return endereco.get();
		// } else {
		// return null;
		// }
		return enderecoRepo.findById(id).orElse(null);
	}

	public Endereco salvarEndereco(Endereco endereco) { // Retorna os dados da instâcia Endereco. E precisa ter os
														// parametros
		// instancia e
		// objeto com os dados..
		return enderecoRepo.save(endereco); // salvar os dados do novo Endereco e salva no objeto endereco.
	}

	public Endereco atualizarEndereco(Endereco endereco) {
		if(endereco == null || buscarEnderecoPorId(endereco.getIdEndereco()) == null) {
			return null;
		}
		
		return enderecoRepo.save(endereco);

	}

	public Boolean deletarEndereco(Endereco endereco) {
		if (endereco == null || buscarEnderecoPorId(endereco.getIdEndereco()) == null) {
	        return false;
	    }

	    enderecoRepo.delete(endereco);
	    
	    return buscarEnderecoPorId(endereco.getIdEndereco()) == null;
	}
}
