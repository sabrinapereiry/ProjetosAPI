package com.api.ecommerce.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.ecommerce.entities.Endereco;
import com.api.ecommerce.repositories.EnderecoRepository;
import com.residencia.biblioteca.dto.ReceitaWsDTO;

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
	
	// teste cep
	public ReceitaWsDTO consultaCep (String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://receitaws.com.br/v1/cnpj/{cnpj}";
		
		Map<String,String> params = new HashMap<String,String>();
		
		params.put("cep", cep);
		
		ReceitaWsDTO receitaDto = restTemplate.getForObject(uri, ReceitaWsDTO.class, params);
		
		return receitaDto;
			
	}
	
	
	
	
	
	
	
	
}
