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

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;
import com.abs.baseConhecimento.api.response.Response;
import com.abs.baseConhecimento.api.services.InformacaoService;


@RestController
@RequestMapping("/api/informacoes")
@CrossOrigin(origins = "*")
public class InformacaoController {

	@Autowired
	private InformacaoService service;
	
	private static final Logger log = LoggerFactory.getLogger(InformacaoController.class);
	
	/**
	 * Remove uma Informação por ID.
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
	 * Atualiza os dados de uma informacao.
	 * 
	 * @param id
	 * @param objDto
	 * @return ResponseEntity<Void>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Long id,
			@Valid @RequestBody InformacaoDTO objDto) {
		Informacao obj = service.fromDtoToInformacao(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Adiciona uma informação.
	 * 
	 * @param InformacaoDTO
	 * @return ResponseEntity<Response<Informacao>>
	 */
	@PostMapping
	public ResponseEntity<Response<Informacao>> insert(@Valid @RequestBody InformacaoDTO objDto) {
		
		Informacao obj = service.fromDtoToInformacao(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Retorna a listagem de informações de um tópico.
	 * 
	 * @param topicoId
	 * @return ResponseEntity<Response<List<InformacaoDto>>>
	 */
	@GetMapping(value = "/topico/{topicoId}")
	public ResponseEntity<Response<List<InformacaoDTO>>> listarPorTopicoId(
			@PathVariable("topicoId") Long topicoId
			) {
		
		log.info("Buscando informações por ID do tópico: {}", topicoId);
		Response<List<InformacaoDTO>> response = new Response<List<InformacaoDTO>>();

		List<Informacao> informacoes = service.buscarPorTopicoId(topicoId);
		
		if (informacoes.isEmpty()) {
			log.info("Nenhuma informação encontrada");
			response.getErrors().add("Nenhuma informação encontrada");
			return ResponseEntity.badRequest().body(response);
		}
		
		List<InformacaoDTO> informacoesDto = informacoes.stream().map(info -> service.fromInformacaoToDto(info))
				.collect(Collectors.toList());   

		response.setData(informacoesDto);
		return ResponseEntity.ok(response);
	}

}
