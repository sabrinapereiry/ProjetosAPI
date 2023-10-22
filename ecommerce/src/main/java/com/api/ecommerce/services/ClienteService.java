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

	public Cliente atualizarCliente(Cliente clienteAtualizado) {
		// Verifique se o cliente atualizado e o cliente existente são válidos
		if (clienteAtualizado == null || clienteRepo.findById(clienteAtualizado.getIdCliente()).isEmpty()) {
			return null;
		}

		// Obtenha o cliente existente
		Cliente clienteExistente = clienteRepo.getReferenceById(clienteAtualizado.getIdCliente());

		// Atualize as informações do cliente com base nas informações do cliente
		// atualizado
		clienteExistente.setNomeCompleto(clienteAtualizado.getNomeCompleto());
		clienteExistente.setEmail(clienteAtualizado.getEmail());
		clienteExistente.setCpf(clienteAtualizado.getCpf());
		clienteExistente.setTelefone(clienteAtualizado.getTelefone());
		clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());

		// Faça outras atualizações necessárias com base nas informações do cliente
		// atualizado.

		// Salve as alterações no cliente no banco de dados
		clienteRepo.save(clienteExistente);

		return clienteExistente; // Retorne o cliente atualizado.
	}

	// Delete
	public Boolean deletarCliente(Cliente cliente) {

		if (cliente == null || buscarClienteId(cliente.getIdCliente()) == null) {
			return false;
		}

		clienteRepo.delete(cliente);

		return buscarClienteId(cliente.getIdCliente()) == null;

	}

}
