package com.abs.baseConhecimento.api.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
	private CategoriaService categoriaService;
	
	/**
	 * Remove um categoria por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Categoria>>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		log.info("Removendo categoria: {}", id);
		Response<String> response = new Response<String>();

		this.categoriaService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	/**
	 * Atualiza os dados de uma categoria.
	 * 
	 * @param id
	 * @param categoriaDTO
	 * @return ResponseEntity<Response<Categoria>>
	 * @throws ParseException 
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<CategoriaDTO>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody CategoriaDTO categoriaDTO, BindingResult result) throws ParseException {
		
		Response<CategoriaDTO> response = new Response<CategoriaDTO>();
		
		Categoria categoria = this.categoriaService.fromDtoToCategoria(categoriaDTO, result);
		log.info("Atualizando categoria: {}", categoria.toString());
		categoria.setId(id);
		if (result.hasErrors()) {
			log.error("Erro validando categoria: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		categoria = this.categoriaService.update(categoria);
		if (categoria == null) {
			log.info("Categoria não encontrada para o ID: {}", id);
			response.getErrors().add("Categoria não encontrada para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.categoriaService.fromCategoriaToDto(categoria));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna uma categoria por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<CategoriaDTO>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<CategoriaDTO>> listarPorId(@PathVariable("id") Long id) {
		log.info("Buscando categoria por ID: {}", id);
		Response<CategoriaDTO> response = new Response<CategoriaDTO>();
		Categoria categoria = this.categoriaService.find(id);

		response.setData(this.categoriaService.fromCategoriaToDto(categoria));
		return ResponseEntity.ok(response);
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
		
		List<Categoria> list = this.categoriaService.list();
		if (list.isEmpty()) {
			log.info("Nenhuma categoria pai encontrada");
			response.getErrors().add("Nenhuma categoria pai encontrada");
			return ResponseEntity.badRequest().body(response);
		}

		List<CategoriaDTO> listDto = list.stream().map(obj -> categoriaService.fromCategoriaToDtoComSubsComTopicos(obj))
				.collect(Collectors.toList());
		
		response.setData(listDto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Adiciona uma nova categoria.
	 * 
	 * @param categoriaDTO
	 * @param result
	 * @return ResponseEntity<Response<CategoriaDTO>>
	 * @throws ParseException 
	 */
	@PostMapping
	public ResponseEntity<Response<CategoriaDTO>> adicionar(@Valid @RequestBody CategoriaDTO categoriaDTO,
			BindingResult result) 
			throws ParseException {

		log.info("Adicionando categoria: {}", categoriaDTO.toString());
		Response<CategoriaDTO> response = new Response<CategoriaDTO>();
		
		Categoria categoria = this.categoriaService.fromDtoToCategoria(categoriaDTO, result);

		if (result.hasErrors()) {
			log.error("Erro validando categoria: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		categoria = this.categoriaService.insert(categoria);
		CategoriaDTO obj = this.categoriaService.fromCategoriaToDto(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
