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

import com.api.ecommerce.dto.RelatorioItemDTO;
import com.api.ecommerce.entities.PedidoItem;
import com.api.ecommerce.services.PedidoItemService;

@RestController
@RequestMapping("/itens")
public class PedidoItemController {

	@Autowired
	PedidoItemService pedidoItemService;

	@PostMapping
	public ResponseEntity<PedidoItem> cadastrarPedidoItem(@RequestBody PedidoItem item) {
		return new ResponseEntity<>(pedidoItemService.cadastrarItem(item), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PedidoItem>> listarPedidoItems() {
		return new ResponseEntity<>(pedidoItemService.listarItems(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoItem> buscarPedidoItemPorId(@PathVariable Long id) {
		return new ResponseEntity<>(pedidoItemService.buscarItemPorId(id), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> atualizar(@RequestBody PedidoItem item) {
		if (pedidoItemService.atualizarItem(item) != null) {
			return new ResponseEntity<>("Atualização realizada com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível atualizar", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletarPedidoItem(@RequestBody PedidoItem item) {
		if (Boolean.TRUE.equals(pedidoItemService.deletarItem(item)))
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		else
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/relatorio_itens")
	public ResponseEntity<List<RelatorioItemDTO>> listarPedidoItens() {
		List<RelatorioItemDTO> itemDTO = pedidoItemService.listarItensDTO();

		if (itemDTO == null) {
			return new ResponseEntity<>(itemDTO, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(itemDTO, HttpStatus.OK);
		}
	}
}
