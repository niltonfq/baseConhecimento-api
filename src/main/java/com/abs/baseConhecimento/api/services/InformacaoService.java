package com.abs.baseConhecimento.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;

public interface InformacaoService {

	List<Informacao> buscarPorTopicoId(Long topicoId);
	
	Optional<Informacao> find(Long id);
	Informacao save(Informacao informacao);
	Informacao update(Informacao informacao);
	void delete(Long id);
	
	InformacaoDTO fromInformacaoToDto(Informacao info);
	Informacao fromDtoToInformacao(InformacaoDTO informacaoDTO, BindingResult result);
}
