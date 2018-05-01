package com.abs.baseConhecimento.api.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
import com.abs.baseConhecimento.api.entities.Categoria;

public interface CategoriaService {

	List<Categoria> list();

	Categoria save(Categoria categoria);

	CategoriaDTO toDto(Categoria categoria);
	Categoria fromDto(CategoriaDTO categoriaDTO, BindingResult result);
	
	
}
