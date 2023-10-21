package com.api.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.dto.RelatorioItemDTO;
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
	
	public List<RelatorioItemDTO> listarItensDTO() {

		List<PedidoItem> itens = pedidoItemRepository.findAll();
		List<RelatorioItemDTO> itensResDTO = new ArrayList<>();

		for (PedidoItem item : itens) {
			RelatorioItemDTO itemResDTO = new RelatorioItemDTO();

			itemResDTO.setIdProduto(item.getProduto().getIdProduto());
			itemResDTO.setNome(item.getProduto().getNome());
			itemResDTO.setValorVenda(item.getValorVenda());
			itemResDTO.setQuantidade(item.getQuantidade());
			itemResDTO.setValorBruto(item.getValorBruto());
			itemResDTO.setPercentualDesconto(item.getPercentualDesconto());
			itemResDTO.setValorLiquido(item.getValorLiquido());
			itensResDTO.add(itemResDTO);
		}
		return itensResDTO;
	}
}
