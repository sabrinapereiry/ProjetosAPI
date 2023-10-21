package com.api.ecommerce.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.ecommerce.entities.Produto;
import com.api.ecommerce.repositories.ProdutoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public Produto cadastrarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto buscarProdutoPorId(Long id) {
		return produtoRepository.findById(id).orElse(null);
	}

	public Produto atualizarProduto(Produto produto) {
		if(produto == null || buscarProdutoPorId(produto.getIdProduto()) == null) {
			return null;
		}
		
		return produtoRepository.save(produto);
	}
	
	public Boolean deletarProduto(Produto produto) {
		if (produto == null || buscarProdutoPorId(produto.getIdProduto()) == null) {
	        return false;
	    }

	    produtoRepository.delete(produto);
	    
	    return buscarProdutoPorId(produto.getIdProduto()) == null;
	}
	
	
	
	// imagem
	public Produto salvarProdutoComFoto(String strProduto, MultipartFile arqImg) throws IOException {
		Produto produto = new Produto();
		
		try {
			ObjectMapper objMp = new ObjectMapper()
					.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			produto = objMp.readValue(strProduto, Produto.class);
		}catch(IOException e) {
			System.out.println("Erro ao converter a string produto" + e.toString());
		}
		
		//arqImg.getName()    pega o nome da imagem
		produto.setImagem(arqImg.getBytes());
		
			return produtoRepository.save(produto);
		
	}
}
