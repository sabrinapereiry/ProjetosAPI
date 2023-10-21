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
		RelatorioPedidoDTO relatorio = new RelatorioPedidoDTO(pedido.getIdPedido(), pedido.getDataPedido(), pedido.getValorTotal());
		emailService.enviarEmail("emailhipotetico@gmail.com", "Novo pedido cadastrado", relatorio.toString());
		return pedidoR.save(pedido);
	}

	public Pedido atualizarPedido(Pedido pedido) {
		if(pedido == null || buscarIdPedido(pedido.getIdPedido()) == null) {
			return null;
		}
		
		return pedidoR.save(pedido);
	}

	public Boolean deletarPedido(Pedido pedido) {
		if (pedido == null || buscarIdPedido(pedido.getIdPedido()) == null) {
	        return false;
	    }

	    pedidoR.delete(pedido);
	    
	    return buscarIdPedido(pedido.getIdPedido()) == null;

	}
}
