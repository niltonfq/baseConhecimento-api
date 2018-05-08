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

import com.abs.baseConhecimento.api.entities.Anexo;
import com.abs.baseConhecimento.api.services.AnexoService;

@RestController
@RequestMapping("/api/anexos")
@CrossOrigin(origins = "*")
public class AnexoController {

//	private static final Logger log = LoggerFactory.getLogger(AnexoController.class);
	
	@Autowired
	private AnexoService service;
	
	/**
	 * Remove um anexo por ID.
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
	 * Atualizar os dados de uma anexo.
	 * 
	 * @param id
	 * @param AnexoDTO
	 * @return ResponseEntity<Void>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(
			@PathVariable("id") Long id,
			@Valid @RequestBody Anexo obj) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Buscar um anexo por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Anexo>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Anexo> find(@PathVariable("id") Long id) {
		Anexo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Adicionar um anexo.
	 * 
	 * @param Anexo
	 * @param result
	 * @return ResponseEntity<Void>
	 */
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Anexo obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Retornar a listagem de anexos de um tópico.
	 * 
	 * @param none
	 * @return ResponseEntity<Response<List<AnexoDTO>>>
	 */
	@GetMapping(value = "/topico/{topicoId}")
	public ResponseEntity<List<Anexo>> listar(@PathVariable("topicoId") Long topicoId) {
		List<Anexo> list = service.findByTopico(topicoId);
		return ResponseEntity.ok(list);
	}
	
}
