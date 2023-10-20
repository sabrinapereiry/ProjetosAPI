package com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.entities.PedidoItem;
import com.api.ecommerce.repositories.PedidoItemRepository;

@Service
public class PedidoItemService {

	@Autowired
	PedidoItemRepository pedidoItemRepository;

	public PedidoItem cadastrarItem(PedidoItem item) {
		return pedidoItemRepository.save(item);
	}

	public List<PedidoItem> listarItems() {
		return pedidoItemRepository.findAll();
	}

	public PedidoItem buscarItemPorId(Long id) {
		return pedidoItemRepository.findById(id).orElse(null);
	}

	public PedidoItem atualizarItem(PedidoItem item) {
		return pedidoItemRepository.save(item);
	}
	
	public Boolean deletarItem(PedidoItem Item) {
		if (Item == null) {
			return false;
		} else {
			if (buscarItemPorId(Item.getIdPedidoItem()) == null) {
				return false;
			} else {
				pedidoItemRepository.delete(Item);

				if (buscarItemPorId(Item.getIdPedidoItem()) == null) {
					return true;
				}else {
					return false;
				}
			}
		}
	}
}
