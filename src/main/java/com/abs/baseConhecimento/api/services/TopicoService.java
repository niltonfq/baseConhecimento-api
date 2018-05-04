package com.abs.baseConhecimento.api.services;

import java.util.Optional;

import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Topico;

public interface TopicoService {

	Optional<Topico> find(Long id);
	Topico save(Topico topico);
	Topico update(Topico topico);
	void delete(Long id);
	
	TopicoDTO fromTopicoToDto(Topico topico);
	Topico fromDtoToTopico(TopicoDTO topicoDTO, BindingResult result);
}
