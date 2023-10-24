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
import org.springframework.web.bind.annotation.RestController;

import com.api.ecommerce.entities.Endereco;
import com.api.ecommerce.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> listarEndereco() {
		return new ResponseEntity<>(enderecoService.listarEndereco(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {// sem parametro
		return new ResponseEntity<>(enderecoService.buscarEnderecoPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.salvarEndereco(endereco), HttpStatus.CREATED);
	}

	@PostMapping("/porcep")
	public ResponseEntity<Endereco> salvarDTO(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.salvarEnderecoDTO(endereco), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<String> atualizar(@RequestBody Endereco endereco) {
		if (enderecoService.atualizarEndereco(endereco) != null) {
			return new ResponseEntity<>("Atualização realizada com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível atualizar", HttpStatus.BAD_REQUEST);
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