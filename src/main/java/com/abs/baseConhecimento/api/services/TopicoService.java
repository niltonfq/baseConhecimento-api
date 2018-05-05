package com.abs.baseConhecimento.api.services;

import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Topico;

public interface TopicoService {

	Topico find(Long id);
	Topico insert(Topico obj);
	Topico update(Topico obj);
	void delete(Long id);
	
	TopicoDTO fromTopicoToDto(Topico topico);
	Topico fromDtoToTopico(TopicoDTO topicoDTO);
}
