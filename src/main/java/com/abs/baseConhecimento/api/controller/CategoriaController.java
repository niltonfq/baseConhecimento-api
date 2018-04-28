package com.abs.baseConhecimento.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.response.Response;
import com.abs.baseConhecimento.api.services.CategoriaService;


@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<Response<List<Categoria>>> listar() {
		
		Response<List<Categoria>> response = new Response<List<Categoria>>();
		
		List<Categoria> categorias = this.categoriaService.listCategoriasPai();
		//Page<Categoria> categorias = this.categoriaService.list(pageRequest);

		if (categorias.isEmpty()) {
			log.info("Nenhuma categoria pai encontrada");
			response.getErrors().add("Nenhuma categoria pai encontrada");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(categorias);
		return ResponseEntity.ok(response);
	}
}
