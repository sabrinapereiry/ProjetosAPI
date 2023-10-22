package com.api.ecommerce.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RelatorioPedidoDTO {
	private Long idPedido; // ID do pedido
	private Date dataPedido; // Data do pedido
	private BigDecimal valorTotal; // Valor total
	// construtor padrão

	public RelatorioPedidoDTO() {
		// Construtor padrão
	}

	// construtor que aceita atributos
	public RelatorioPedidoDTO(Long idPedido, Date dataPedido, BigDecimal valorTotal) {
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
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

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	// Método que fornece uma representação de String do objeto para fins de
	// apuração
	@Override
	public String toString() {
		return "Relatorio do pedido [ID do pedido = " + idPedido + ", Data do pedido = " + dataPedido
				+ ", Valor Total = " + valorTotal + "]";
	}

}
