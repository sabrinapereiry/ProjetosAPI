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

import com.api.ecommerce.entities.Pedido;
import com.api.ecommerce.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos(){
		return new
				ResponseEntity<>(pedidoService.listarPedidos(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		Pedido pedido = pedidoService.buscarIdPedido(id);
		
		if(pedido ==null) {
			return new
					ResponseEntity<>(pedido,HttpStatus.NOT_FOUND);
		}else {
			return new
					ResponseEntity<>(pedido,HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<Pedido> salvarPedido(@RequestBody Pedido pedido) {
		return new
				ResponseEntity<>(pedidoService.salvarPedido(pedido),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Pedido> atualizarPedido(@RequestBody Pedido pedido) {
		return new
				ResponseEntity<>(pedidoService.atualizarPedido(pedido), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deletarPedido(@RequestBody Pedido pedido) {
		if(pedidoService.deletarPedido(pedido)) {
			return new
					ResponseEntity<>("{\"msg\":\"Deletado com Sucesso\"}", HttpStatus.OK);
		}else {
				return new
						ResponseEntity<>("{\"msg\":\"Não foi possível deletar\"}", HttpStatus.BAD_REQUEST);
		}
	}
}
