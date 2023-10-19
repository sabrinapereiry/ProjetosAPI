package com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.entities.Cliente;
import com.api.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;

	public List<Cliente> listarClientes() {
		return clienteRepo.findAll();

	}

	// Get
	public Cliente buscarClienteId(Long id) {
		return clienteRepo.findById(id).orElse(null);
	}

	// Post
	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	// Put
	public Cliente atualizarCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	// Delete
	public Boolean deletarCliente(Cliente cliente) {
		if (cliente == null)
			return false;

		Cliente clienteExistente = buscarClienteId(cliente.getIdCliente());
		if (clienteExistente == null)
			return false;

		clienteRepo.delete(cliente);

		Cliente clienteContinuaExistente = buscarClienteId(cliente.getIdCliente());

		if (clienteContinuaExistente == null)
			return true;

		return false;

	}
}
