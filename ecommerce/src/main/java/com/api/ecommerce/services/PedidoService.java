package com.api.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.dto.RelatorioPedidoDTO;
import com.api.ecommerce.entities.Pedido;
import com.api.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoR;

	@Autowired
	EmailService emailService;
	
	public List<Pedido> listarPedidos() {
		return pedidoR.findAll();
	}

	public List<RelatorioPedidoDTO> listarRelatorioPedidoDTO() {
		List<Pedido> pedido = pedidoR.findAll();
		List<RelatorioPedidoDTO> relatorioDTO = new ArrayList<>();

		for (Pedido pedidos : pedido) {
			RelatorioPedidoDTO pedidoDTO = new RelatorioPedidoDTO();
			pedidoDTO.setIdPedido(pedidos.getIdPedido());
			pedidoDTO.setDataPedido(pedidos.getDataPedido());
			pedidoDTO.setValorTotal(pedidos.getValorTotal());
			relatorioDTO.add(pedidoDTO);
		}
		return relatorioDTO;
	}

	public Pedido buscarIdPedido(Long id) {
		return pedidoR.findById(id).orElse(null);
	}

	public Pedido salvarPedido(Pedido pedido) {
		Pedido newPedido = pedidoR.save(pedido);
		emailService.enviarEmail("emailhipotetico@gmail.com", "Novo pedido cadastrado", newPedido.toString());
		return newPedido;
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
