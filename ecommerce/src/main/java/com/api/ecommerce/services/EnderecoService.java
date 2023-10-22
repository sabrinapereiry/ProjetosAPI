package com.api.ecommerce.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.ecommerce.entities.Cliente;
import com.api.ecommerce.entities.Endereco;
import com.api.ecommerce.repositories.EnderecoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;

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
		if (endereco == null || buscarEnderecoPorId(endereco.getIdEndereco()) == null) {
			return null;
		}

		return enderecoRepo.save(endereco);

	}

	public void atualizarEnderecoCliente(Cliente cliente, String cep) {
		// Crie uma instância do RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Faça uma chamada à API ViaCEP para obter os dados do CEP
		String viaCEPUrl = "https://viacep.com.br/ws/" + cep + "/json/";
		String viaCEPResponse = restTemplate.getForObject(viaCEPUrl, String.class);

		// Você pode usar a biblioteca Jackson do Spring para mapear a resposta para um
		// objeto se necessário
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Mapeia e tranfforma a resposta JSON para um objeto Map Java
			Map<String, Object> cepData = objectMapper.readValue(viaCEPResponse,
					new TypeReference<Map<String, Object>>() {
					});

			// Verifique se os campos necessários estão presentes no mapa e atualize o
			// endereço do cliente
			if (cepData.containsKey("logradouro")) {
				cliente.getEndereco().setRua(cepData.get("logradouro").toString());
			}
			if (cepData.containsKey("bairro")) {
				cliente.getEndereco().setBairro(cepData.get("bairro").toString());
			}
			if (cepData.containsKey("localidade")) {
				cliente.getEndereco().setCidade(cepData.get("localidade").toString());
			}
			if (cepData.containsKey("uf")) {
				cliente.getEndereco().setUf(cepData.get("uf").toString());
			}
			if (cepData.containsKey("cep")) {
				cliente.getEndereco().setCep(cepData.get("cep").toString());
			}

			// Salva as alterações no endereço do cliente
			enderecoRepo.save(cliente.getEndereco());
		} catch (IOException e) {
			e.printStackTrace();
			// exceções de mapeamento de JSON aqui
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public Boolean deletarEndereco(Endereco endereco) {
		if (endereco == null || buscarEnderecoPorId(endereco.getIdEndereco()) == null) {
			return false;
		}

		enderecoRepo.delete(endereco);

		return buscarEnderecoPorId(endereco.getIdEndereco()) == null;
	}
}
