package com.abs.baseConhecimento.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
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
	public ResponseEntity<Response<List<CategoriaDTO>>> listar() {
		
		Response<List<CategoriaDTO>> response = new Response<List<CategoriaDTO>>();
		
		List<Categoria> list = this.categoriaService.list();
		if (list.isEmpty()) {
			log.info("Nenhuma categoria pai encontrada");
			response.getErrors().add("Nenhuma categoria pai encontrada");
			return ResponseEntity.badRequest().body(response);
		}

		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		response.setData(listDto);
		return ResponseEntity.ok(response);
	}
}
