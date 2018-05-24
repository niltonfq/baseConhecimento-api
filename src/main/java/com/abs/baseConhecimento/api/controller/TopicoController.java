package com.abs.baseConhecimento.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import com.abs.baseConhecimento.api.dtos.TopicoCategoriaDTO;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.services.TopicoService;

@RestController
@RequestMapping("/api/topicos")
@CrossOrigin(origins = "*")
public class TopicoController {

//	private static final Logger log = LoggerFactory.getLogger(TopicoController.class);
	
	@Autowired
	private TopicoService service;
	
	/**
	 * Adicionar um topicoCategoria.
	 * 
	 * @param TopicoCategoriaDto
	 * @return ResponseEntity<Void>
	 */
	@PostMapping(value = "/categoria")
	public ResponseEntity<Void> insertTopicoCategoria(@Valid @RequestBody TopicoCategoriaDTO obj) {
		
		service.insertTopicoCategoria(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	/**
	 * Remove um topico por ID.
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
	 * Remove um topico por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping(value = "/{idTopico}/categorias/{idCategoria}")
	public ResponseEntity<Void> deleteTopicoCategoria(
			@PathVariable("idTopico") Long idTopico,
			@PathVariable("idCategoria") Long idCategoria
			) {
		service.deleteTopicoCategoria(idTopico, idCategoria);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Atualizar os dados de uma topico.
	 * 
	 * @param id
	 * @param TopicoDTO
	 * @return ResponseEntity<Void>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(
			@PathVariable("id") Long id,
			@Valid @RequestBody Topico obj) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Buscar um topico por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Topico>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Topico> find(@PathVariable("id") Long id) {
		Topico obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Adicionar um topico.
	 * 
	 * @param Topico
	 * @return ResponseEntity<Void>
	 */
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Topico obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Retornar a listagem de topicos de um tópico.
	 * 
	 * @param none
	 * @return ResponseEntity<Response<List<TopicoDTO>>>
	 */
	@GetMapping(value = "/categorias/{topicoId}")
	public ResponseEntity<List<CategoriaDTO>> listar(@PathVariable("topicoId") Long topicoId) {
		List<CategoriaDTO> list = service.findByIdTopico(topicoId);
		return ResponseEntity.ok(list);
	}
	
}
