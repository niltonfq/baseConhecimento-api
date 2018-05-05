package com.abs.baseConhecimento.api.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;

public interface InformacaoService {

	List<Informacao> buscarPorTopicoId(Long topicoId);
	
	Informacao find(Long id);
	Informacao insert(Informacao obj);
	Informacao update(Informacao obj);
	void delete(Long id);
	
	InformacaoDTO fromInformacaoToDto(Informacao obj);
	Informacao fromDtoToInformacao(InformacaoDTO dto, BindingResult result);
}
