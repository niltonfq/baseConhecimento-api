package com.abs.baseConhecimento.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	private CategoriaService service;
	
	/**
	 * Remove um categoria por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Atualiza os dados de uma categoria.
	 * 
	 * @param id
	 * @param CategoriaDTO
	 * @return ResponseEntity<Void>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(
			@PathVariable("id") Long id,
			@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.fromDtoToCategoria(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Retorna uma categoria por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Categoria>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable("id") Long id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Adiciona uma nova categoria.
	 * 
	 * @param categoriaDTO
	 * @param result
	 * @return ResponseEntity<Void>
	 */
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria obj = service.fromDtoToCategoria(categoriaDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Retorna a listagem de categorias e seus tópicos existentes.
	 * 
	 * @param none
	 * @return ResponseEntity<Response<List<CategoriaDTO>>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<CategoriaDTO>>> listar() {
		
		Response<List<CategoriaDTO>> response = new Response<List<CategoriaDTO>>();
		
		List<Categoria> list = service.list();
		if (list.isEmpty()) {
			log.info("Nenhuma categoria pai encontrada");
			response.getErrors().add("Nenhuma categoria pai encontrada");
			return ResponseEntity.badRequest().body(response);
		}

		List<CategoriaDTO> listDto = list.stream().map(obj -> service.fromCategoriaToDtoComSubsComTopicos(obj))
				.collect(Collectors.toList());
		
		response.setData(listDto);
		return ResponseEntity.ok(response);
	}
	
}
