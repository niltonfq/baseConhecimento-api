package com.abs.baseConhecimento.api.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
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

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;
import com.abs.baseConhecimento.api.response.Response;
import com.abs.baseConhecimento.api.services.InformacaoService;


@RestController
@RequestMapping("/api/informacoes")
@CrossOrigin(origins = "*")
public class InformacaoController {

	@Autowired
	private InformacaoService informacaoService;
	
	private static final Logger log = LoggerFactory.getLogger(InformacaoController.class);
	
	/**
	 * Remove uma Informação por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		log.info("Removendo categoria: {}", id);
		Response<String> response = new Response<String>();
		Informacao categoria = this.informacaoService.find(id);


		this.informacaoService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	/**
	 * Atualiza os dados de uma informacao.
	 * 
	 * @param id
	 * @param informacaoDTO
	 * @return ResponseEntity<Response<Categoria>>
	 * @throws ParseException 
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<InformacaoDTO>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody InformacaoDTO informacaoDTO, BindingResult result) throws ParseException {
		
		Response<InformacaoDTO> response = new Response<InformacaoDTO>();
		
		Informacao info = this.informacaoService.fromDtoToInformacao(informacaoDTO, result);
		log.info("Atualizando Informacao: {}", info.toString());
		info.setId(id);
		if (result.hasErrors()) {
			log.error("Erro validando Informacao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		info = this.informacaoService.update(info);
		if (info == null) {
			log.info("Informacao não encontrada para o ID: {}", id);
			response.getErrors().add("Informacao não encontrada para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.informacaoService.fromInformacaoToDto(info));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Adiciona uma informação.
	 * 
	 * @param informacaoDTO
	 * @param result
	 * @return ResponseEntity<Response<InformacaoDTO>>
	 * @throws ParseException 
	 */
	@PostMapping
	public ResponseEntity<Response<InformacaoDTO>> adicionar(@Valid @RequestBody InformacaoDTO informacaoDTO,
			BindingResult result) throws ParseException {
		
		informacaoDTO.setId(null);
		log.info("Adicionando informacao: {}", informacaoDTO.toString());
		Response<InformacaoDTO> response = new Response<InformacaoDTO>();
		
		Informacao info = this.informacaoService.fromDtoToInformacao(informacaoDTO, result);

		if (result.hasErrors()) {
			log.error("Erro validando informacao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		info = this.informacaoService.insert(info);
		InformacaoDTO obj = this.informacaoService.fromInformacaoToDto(info);
		
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

		List<Informacao> informacoes = this.informacaoService.buscarPorTopicoId(topicoId);
		
		if (informacoes.isEmpty()) {
			log.info("Nenhuma informação encontrada");
			response.getErrors().add("Nenhuma informação encontrada");
			return ResponseEntity.badRequest().body(response);
		}
		
		List<InformacaoDTO> informacoesDto = informacoes.stream().map(info -> this.informacaoService.fromInformacaoToDto(info))
				.collect(Collectors.toList());   

		response.setData(informacoesDto);
		return ResponseEntity.ok(response);
	}

}
