package com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.entities.Pedido;
import com.api.ecommerce.repositories.PedidoRepository;


@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoR;
	
	public List<Pedido> listarPedidos(){
		return pedidoR.findAll();
	}
	
	public Pedido buscarIdPedido(Long id) {
		return pedidoR.findById(id).orElse(null);
	}
	
	public Pedido salvarPedido(Pedido pedido) {
		return pedidoR.save(pedido);
	}
	
	public Pedido atualizarPedido(Pedido pedido) {
		return pedidoR.save(pedido);
	}
	
	public Boolean deletarPedido(Pedido pedido) {
		if (pedido == null)
			return false;

		Pedido pedidoExistente = buscarIdPedido(pedido.getIdPedido());

		if (pedidoExistente == null)
			return false;

		pedidoR.delete(pedido);

		Pedido pedidoExiste = buscarIdPedido(pedido.getIdPedido());

		if (pedidoExiste == null)
			return true;

		return false;

	}
}
