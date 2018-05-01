package com.abs.baseConhecimento.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
		List<InformacaoDTO> informacoesDto = informacoes.stream().map(info -> this.informacaoService.toDto(info))
				.collect(Collectors.toList());   

		response.setData(informacoesDto);
		return ResponseEntity.ok(response);
	}

}
