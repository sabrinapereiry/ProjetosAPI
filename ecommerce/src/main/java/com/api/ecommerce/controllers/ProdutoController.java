package com.api.ecommerce.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.ecommerce.entities.Produto;
import com.api.ecommerce.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.cadastrarProduto(produto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		return new ResponseEntity<>(produtoService.listarProdutos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
		return new ResponseEntity<>(produtoService.buscarProdutoPorId(id), HttpStatus.OK);

	}

	@PutMapping
	public ResponseEntity<String> atualizar(@RequestBody Produto produto) {
		if (produtoService.atualizarProduto(produto) != null) {
			return new ResponseEntity<>("Atualização realizada com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível atualizar", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletarProduto(@RequestBody Produto produto) {
		if (Boolean.TRUE.equals(produtoService.deletarProduto(produto)))
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		else
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}

	// imagem
	@PostMapping("/prodimg")
	public ResponseEntity<Produto> salvarComFtoto(@RequestPart("produto") String strProduto,
			@RequestPart("img") MultipartFile arqImg) throws IOException {
		return new ResponseEntity<>(produtoService.salvarProdutoComFoto(strProduto, arqImg), HttpStatus.CREATED);
	}

}
