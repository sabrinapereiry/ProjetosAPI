package com.api.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ecommerce.entities.Categoria;
import com.api.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public Categoria cadastrarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}

	public Categoria buscarCategoriaPorId(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	public Categoria atualizarCategoria(Categoria categoria) {
		if(categoria == null || buscarCategoriaPorId(categoria.getIdCategoria()) == null) {
			return null;
		}
		
		return categoriaRepository.save(categoria);
	}
	
	public Boolean deletarCategoria(Categoria categoria) {
	    if (categoria == null || buscarCategoriaPorId(categoria.getIdCategoria()) == null) {
	        return false;
	    }

	    categoriaRepository.delete(categoria);
	    
	    return buscarCategoriaPorId(categoria.getIdCategoria()) == null;
	}
	
	
}
