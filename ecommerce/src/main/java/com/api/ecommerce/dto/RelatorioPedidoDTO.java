package com.api.ecommerce.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RelatorioPedidoDTO {
	private Long idPedido; // ID do pedido
	private Date dataPedido; // Data do pedido
	private BigDecimal valorTotal; // Valor total
	// private List<ItemPedidoDTO> itensPedido; // Relação de itens do pedido

	public RelatorioPedidoDTO() {
		// Construtor padrão
	}

	public RelatorioPedidoDTO(Long idPedido, Date dataPedido, BigDecimal valorTotal) {
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		// this.itensPedido = itensPedido;
	}

	// Getters e Setters

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal( BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	// public List<ItemPedido> getItensPedido() {
	// return itensPedido;
	// }

	// public void setItensPedido(List<ItemPedido> itensPedido) {
	// this.itensPedido = itensPedido;
	// }
}
