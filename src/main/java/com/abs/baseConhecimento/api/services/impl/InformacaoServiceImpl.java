package com.abs.baseConhecimento.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;
import com.abs.baseConhecimento.api.repositories.InformacaoRepository;
import com.abs.baseConhecimento.api.services.InformacaoService;

@Service
public class InformacaoServiceImpl implements InformacaoService{

	@Autowired
	private InformacaoRepository repo;
	
	@Override
	public List<Informacao> buscarPorTopicoId(Long topicoId) {
		return repo.findByTopicoId(topicoId);
	}

	@Override
	public InformacaoDTO toDto(Informacao info) {
		return new InformacaoDTO(info.getId(), info.getDescricao());
	}

}
