package com.api.ecommerce.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idPedidoItem",
		scope=PedidoItem.class
	)
@Entity
@Table(name = "pedido_item")
public class PedidoItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido_item")
	private Long idPedidoItem;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "percentual_desconto")
	private Double percentualDesconto;

	@Column(name = "valor_venda")
	private BigDecimal valorVenda;

	@Column(name = "valor_bruto")
	private BigDecimal valorBruto;

	@Column(name = "valor_liquido")
	private BigDecimal valorLiquido;
	
	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", nullable = false)
	private Produto pedido;

	public Long getIdPedidoItem() {
		return idPedidoItem;
	}

	public void setIdPedidoItem(Long idPedidoItem) {
		this.idPedidoItem = idPedidoItem;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getPedido() {
		return pedido;
	}

	public void setPedido(Produto pedido) {
		this.pedido = pedido;
	}
}
