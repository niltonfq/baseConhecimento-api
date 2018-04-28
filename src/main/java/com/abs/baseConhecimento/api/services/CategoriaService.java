package com.abs.baseConhecimento.api.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.abs.baseConhecimento.api.entities.Categoria;

public interface CategoriaService {

	List<Categoria> listCategoriasPai();

	Page<Categoria> list(PageRequest pageRequest);
	
}
