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

import com.api.ecommerce.entities.PedidoItem;
import com.api.ecommerce.services.PedidoItemService;

@RestController
@RequestMapping("/itens")
public class PedidoItemController {
	
	@Autowired
	PedidoItemService pedidoItemService;
	
	@PostMapping
	public ResponseEntity<PedidoItem> cadastrarPedidoItem(@RequestBody PedidoItem item){
		return new ResponseEntity<>(pedidoItemService.cadastrarItem(item), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PedidoItem>> listarPedidoItems(){
		return new ResponseEntity<>(pedidoItemService.listarItems(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoItem> buscarPedidoItemPorId(@PathVariable Long id){
		PedidoItem item = pedidoItemService.buscarItemPorId(id);
		
		if(item == null) {
			return new ResponseEntity<>(item, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(item, HttpStatus.OK);
		}
	}
	
	@PutMapping
	public ResponseEntity<PedidoItem> atualizar(@RequestBody PedidoItem item) {
		return new ResponseEntity<>(pedidoItemService.atualizarItem(item), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deletarPedidoItem(@RequestBody PedidoItem item) {
		if (Boolean.TRUE.equals(pedidoItemService.deletarItem(item)))
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		else
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}
}
