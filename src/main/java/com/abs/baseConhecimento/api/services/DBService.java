package com.abs.baseConhecimento.api.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.CategoriaSubCategoria;
import com.abs.baseConhecimento.api.repositories.CategoriaRepository;
import com.abs.baseConhecimento.api.repositories.CategoriaSubCategoriaRepository;


@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CategoriaSubCategoriaRepository categoriaSubCategoriaRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat2 = new Categoria(2, "Php");
		Categoria cat5 = new Categoria(5, "Comandos");
		categoriaRepository.saveAll(Arrays.asList(cat2,cat5));
		
		//CategoriaSubCategoria csc = new CategoriaSubCategoria(cat2, cat5);
		//categoriaSubCategoriaRepository.saveAll(Arrays.asList(csc));
	}
}
