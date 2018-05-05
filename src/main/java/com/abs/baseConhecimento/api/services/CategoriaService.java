package com.abs.baseConhecimento.api.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
import com.abs.baseConhecimento.api.entities.Categoria;

public interface CategoriaService {

	List<Categoria> list();
	Categoria find(Long id);
	Categoria insert(Categoria obj);
	Categoria update(Categoria obj);
	void delete(Long id);

	CategoriaDTO fromCategoriaToDto(Categoria obj);
	CategoriaDTO fromCategoriaToDtoComSubsComTopicos(Categoria obj);
	Categoria fromDtoToCategoria(CategoriaDTO categoriaDTO, BindingResult result);

}
