package com.api.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
		return new ResponseEntity<>(produtoService.cadastrarProduto(produto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos(){
		return new ResponseEntity<>(produtoService.listarProdutos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarProdutoPorId(@PathVariable Long id){
		Produto produto = produtoService.buscarProdutoPorId(id);
		
		if(produto == null) {
			return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(produto, HttpStatus.OK);
		}
	}
	
	@PutMapping
	public ResponseEntity<String> atualizar(@RequestBody Produto produto) {
		if (produtoService.atualizarProduto(produto) != null) {
			return new ResponseEntity<>("Atualização realizada com sucesso", HttpStatus.OK);
		}else {
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
	
	
	
	// novo
	 @PostMapping("/upload-imagem/{idProduto}")
	    public ResponseEntity<String> uploadImagem(@PathVariable Long idProduto, @RequestParam("file") MultipartFile file) {
	        Produto produto = produtoService.buscarProdutoPorId(idProduto);
	        if (produto == null) {
	            return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
	        }

	        try {
	            byte[] imagemBytes = file.getBytes();
	            produto.setImagem(imagemBytes);
	            produtoService.atualizarProduto(produto);
	            return new ResponseEntity<>("Imagem carregada com sucesso", HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Erro ao carregar a imagem", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	@GetMapping("/download-imagem/{idProduto}")
    public ResponseEntity<byte[]> downloadImagem(@PathVariable Long idProduto) {
        Produto produto = produtoService.buscarProdutoPorId(idProduto);
        if (produto == null || produto.getImagem() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(produto.getImagem().length);

        return new ResponseEntity<>(produto.getImagem(), headers, HttpStatus.OK);
    }
	
	
	
	
	
}
