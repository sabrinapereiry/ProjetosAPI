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

import com.api.ecommerce.entities.Cliente;
import com.api.ecommerce.services.ClienteService;

@RestController
@RequestMapping("/Clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes() {
		return new ResponseEntity<>(clienteService.listarClientes(), HttpStatus.OK);
	}

	// Resgata por id
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<>(clienteService.buscarClienteId(id), HttpStatus.OK); // 200
	}

	// Salva
	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.salvarCliente(cliente), HttpStatus.CREATED);
	}

	// Atualiza
	@PutMapping
	public ResponseEntity<String> atualizar(@RequestBody Cliente cliente) {
		if (clienteService.atualizarCliente(cliente) != null) {
			return new ResponseEntity<>("Atualização realizada com sucesso", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Não foi possível atualizar", HttpStatus.BAD_REQUEST);
		}
	}

	// Deleta
	@DeleteMapping
	public ResponseEntity<String> deletarCliente(@RequestBody Cliente cliente) {
		if (clienteService.deletarCliente(cliente))
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);

		else
			return new ResponseEntity<>("Não foi possivel deletar", HttpStatus.BAD_REQUEST);
	}
}
