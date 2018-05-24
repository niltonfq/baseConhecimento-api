package com.abs.baseConhecimento.api.services;

import java.util.List;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
import com.abs.baseConhecimento.api.dtos.TopicoCategoriaDTO;
import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;

public interface TopicoService {

	Topico find(Long id);
	Topico insert(Topico obj);
	Topico update(Topico obj);
	void delete(Long id);
	
	TopicoDTO fromTopicoToDto(Topico topico);
	Topico fromDtoToTopico(TopicoDTO topicoDTO);
	
	List<CategoriaDTO> findByIdTopico(Long idTopico);
	void deleteTopicoCategoria(Long idTopico, Long idCategoria);
	
	TopicoCategoria insertTopicoCategoria(TopicoCategoriaDTO obj);
}
