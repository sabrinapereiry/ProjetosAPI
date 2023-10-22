package com.api.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.ecommerce.entities.Cliente;
import com.api.ecommerce.entities.Endereco;
import com.api.ecommerce.services.ClienteService;
import com.api.ecommerce.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Endereco>> listarEndereco() {
		return new ResponseEntity<>(enderecoService.listarEndereco(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarEnderecoPorId(@PathVariable Long id) {// sem parametro
		Endereco endereco = enderecoService.buscarEnderecoPorId(id);

		if (endereco == null) {
			return new ResponseEntity<>("Endereco não encontrado", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(endereco, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.salvarEndereco(endereco), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<String> atualizar(@RequestBody Endereco endereco) {
		if (enderecoService.atualizarEndereco(endereco) != null) {
			return new ResponseEntity<>("Atualização realizada com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível atualizar", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}/endereco")
	public ResponseEntity<String> atualizarEnderecoPorCEP(@PathVariable Long id, @RequestParam String cep) {
		// Aqui, começamos por tentar encontrar um cliente pelo ID fornecido na URL
		Cliente cliente = clienteService.buscarClienteId(id);

		if (cliente != null) {
			// Se encontrarmos o cliente, chamamos o serviço para atualizar as informações
			// do endereço
			// Passamos o ID do cliente (que já foi obtido), 'null' para indicar que não
			// queremos atualizar outros detalhes do cliente,
			// e o CEP que desejamos usar para atualizar o endereço.

			// O serviço fará a solicitação à API ViaCEP para obter as informações do CEP e,
			// em seguida,
			// atualizará o endereço do cliente no banco de dados com essas informações.

			// Em seguida, retornamos uma resposta HTTP com status 'OK' para indicar que a
			// atualização foi bem-sucedida.
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			// Se não encontrarmos o cliente com o ID fornecido, retornamos uma resposta
			// HTTP com status 'NOT FOUND'
			// para indicar que o cliente não foi encontrado, juntamente com uma mensagem de
			// erro personalizada.
			return new ResponseEntity<String>("Cliente não encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletarEndereco(@RequestBody Endereco endereco) {
		if (Boolean.TRUE.equals(enderecoService.deletarEndereco(endereco))) {
			return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível deletar!", HttpStatus.BAD_REQUEST);
		}
	}

}
