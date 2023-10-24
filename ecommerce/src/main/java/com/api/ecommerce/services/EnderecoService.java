package com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.ecommerce.dto.EnderecoDTO;
import com.api.ecommerce.entities.Endereco;
import com.api.ecommerce.exceptions.NoSuchElementException;
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
		return enderecoRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Endereco", id));
	}
	
	public Endereco salvarEndereco(Endereco endereco) {
		return enderecoRepo.save(endereco);
	}

	public Endereco salvarEnderecoDTO(Endereco endereco) {
		// Crie uma instância do RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Faça uma chamada à API ViaCEP para obter os dados do CEP
		String viaCEPUrl = "https://viacep.com.br/ws/" + endereco.getCep() + "/json";
		EnderecoDTO enderecoDTO = restTemplate.getForObject(viaCEPUrl, EnderecoDTO.class);
		try {

			endereco.setRua(enderecoDTO.getLogradouro());
			endereco.setBairro(enderecoDTO.getBairro());
			endereco.setCidade(enderecoDTO.getLocalidade());
			endereco.setComplemento(enderecoDTO.getComplemento());
			endereco.setUf(enderecoDTO.getUf());

			return enderecoRepo.save(endereco);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Endereco atualizarEndereco(Endereco endereco) {
		if (endereco == null || buscarEnderecoPorId(endereco.getIdEndereco()) == null) {
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