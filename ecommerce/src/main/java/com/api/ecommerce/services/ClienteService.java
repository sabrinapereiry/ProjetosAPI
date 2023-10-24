package com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.entities.Cliente;
import com.api.ecommerce.exceptions.NoSuchElementException;
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
		return clienteRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Cliente", id));
	}

	// Post
	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	// Put
	public Cliente atualizarCliente(Cliente cliente) {
		if(cliente == null || buscarClienteId(cliente.getIdCliente()) == null) {
			return null;
		}
		
		return clienteRepo.save(cliente);
	}

	// Delete
	public Boolean deletarCliente(Cliente cliente) {
		if (cliente == null) {
            return false;
        }

        if (clienteRepo.existsById(cliente.getIdCliente())) {
            clienteRepo.delete(cliente);
            return true; 
        } else {
            return false; 
        }
	}
}
