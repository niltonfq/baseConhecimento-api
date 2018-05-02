package com.abs.baseConhecimento.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
import com.abs.baseConhecimento.api.entities.Categoria;

public interface CategoriaService {

	List<Categoria> list();
	Optional<Categoria> find(Long id);
	Categoria save(Categoria categoria);
	Categoria update(Categoria categoria);

	CategoriaDTO fromCategoriaToDto(Categoria categoria);
	CategoriaDTO fromCategoriaToDtoComSubsComTopicos(Categoria categoria);
	Categoria fromDtoToCategoria(CategoriaDTO categoriaDTO, BindingResult result);


	
}
