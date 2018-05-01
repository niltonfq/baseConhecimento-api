package com.abs.baseConhecimento.api.services;

import java.util.List;

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;

public interface InformacaoService {

	List<Informacao> buscarPorTopicoId(Long topicoId);
	InformacaoDTO toDto(Informacao info);
	
}
