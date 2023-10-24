package com.api.ecommerce.dto;

public class EnderecoDTO {

	private String cep;

	private String logradouro;

	private String bairro;

	private String localidade;

	private String numero;

	private String complemento;

	private String uf;

	public EnderecoDTO(String cep, String logradouro, String bairro, String localidade, String numero, String complemento,
			String uf) {

		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.numero = numero;
		this.complemento = complemento;
		this.uf = uf;
	}

	public EnderecoDTO() {
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}