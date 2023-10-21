package com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.entities.Produto;
import com.api.ecommerce.repositories.ProdutoRepository;

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
}
