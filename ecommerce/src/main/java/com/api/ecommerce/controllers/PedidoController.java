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

import com.api.ecommerce.dto.RelatorioPedidoDTO;
import com.api.ecommerce.entities.Pedido;
import com.api.ecommerce.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos() {
		return new ResponseEntity<>(pedidoService.listarPedidos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<>(pedidoService.buscarIdPedido(id), HttpStatus.OK);
	}

	@GetMapping("/relatorio-pedidos") // o mapping não pode ser igual.
	public ResponseEntity<List<RelatorioPedidoDTO>> listarRelatorioPedidoDTO() {
		List<RelatorioPedidoDTO> pedidoDTO = pedidoService.listarRelatorioPedidoDTO();
		if (pedidoDTO == null) {
			return new ResponseEntity<>(pedidoDTO, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<Pedido> salvarPedido(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.salvarPedido(pedido), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<String> atualizarPedido(@RequestBody Pedido pedido) {
		if (pedidoService.atualizarPedido(pedido) != null) {
			return new ResponseEntity<>("Atualização realizada com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível atualizar", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/valor-total")
	public ResponseEntity<String> atualizarValorTotal(@RequestBody Pedido pedido) {
		if (pedidoService.atualizarValorTotal(pedido) != null) {
			return new ResponseEntity<>("Atualização realizada com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível atualizar", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletarPedido(@RequestBody Pedido pedido) {
		if (Boolean.TRUE.equals(pedidoService.deletarPedido(pedido))) {
			return new ResponseEntity<>("{\"msg\":\"Deletado com Sucesso\"}", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("{\"msg\":\"Não foi possível deletar\"}", HttpStatus.BAD_REQUEST);
		}
	}
}
