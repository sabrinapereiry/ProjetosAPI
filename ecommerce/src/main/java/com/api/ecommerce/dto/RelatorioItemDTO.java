package com.api.ecommerce.dto;

import java.math.BigDecimal;

public class RelatorioItemDTO {

	private Long idProduto;
	private String nome;
	private BigDecimal valorVenda;
	private Integer quantidade;
	private BigDecimal valorBruto;
	private Double percentualDesconto;
	private BigDecimal valorLiquido;

	public RelatorioItemDTO() {
	}

	public RelatorioItemDTO(Long idProduto, String nome, BigDecimal valorVenda, Integer quantidade,
			BigDecimal valorBruto, Double percentualDesconto, BigDecimal valorLiquido) {
		this.idProduto = idProduto;
		this.nome = nome;
		this.valorVenda = valorVenda;
		this.quantidade = quantidade;
		this.valorBruto = valorBruto;
		this.percentualDesconto = percentualDesconto;
		this.valorLiquido = valorLiquido;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

}