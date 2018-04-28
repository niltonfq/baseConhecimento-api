package com.abs.baseConhecimento.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.repositories.CategoriaRepository;
import com.abs.baseConhecimento.api.services.CategoriaService;


@Service
public class CategoriaServiceImpl implements CategoriaService{

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

	@Autowired
	private CategoriaRepository repo;

	@Override
	public List<Categoria> listCategoriasPai() {
		log.info("Listando categorias pais");
		return repo.listCategoriasPai();
	}

	@Override
	public Page<Categoria> list(PageRequest pageRequest) {
		return repo.findAll(pageRequest);
	}
	
	
}
