package com.api.ecommerce.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.dto.RelatorioItemDTO;
import com.api.ecommerce.entities.PedidoItem;
import com.api.ecommerce.exceptions.NoSuchElementException;
import com.api.ecommerce.repositories.PedidoItemRepository;

@Service
public class PedidoItemService {

	@Autowired
	PedidoItemRepository pedidoItemRepository;

	public PedidoItem cadastrarItem(PedidoItem item) {
		item.setValorBruto(item.getValorVenda().multiply(item.getQuantidade()));
		item.setPercentualDesconto(item.getValorBruto().multiply(item.getPercentualDesconto().divide(BigDecimal.valueOf(100))));
		item.setValorLiquido(item.getValorBruto().subtract(item.getPercentualDesconto()));
		return pedidoItemRepository.save(item);
	}

	public List<PedidoItem> listarItems() {
		return pedidoItemRepository.findAll();
	}

	public PedidoItem buscarItemPorId(Long id) {
		return pedidoItemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("PedidoItem", id));
	}

	public PedidoItem atualizarItem(PedidoItem item) {
		if(item == null || buscarItemPorId(item.getIdPedidoItem()) == null) {
			return null;
		}
		
		return pedidoItemRepository.save(item);
	}
	
	public Boolean deletarItem(PedidoItem item) {
		if (item == null || buscarItemPorId(item.getIdPedidoItem()) == null) {
	        return false;
	    }

	    pedidoItemRepository.delete(item);
	    
	    return buscarItemPorId(item.getIdPedidoItem()) == null;

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
