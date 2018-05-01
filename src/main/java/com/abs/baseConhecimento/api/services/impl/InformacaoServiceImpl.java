package com.abs.baseConhecimento.api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;
import com.abs.baseConhecimento.api.services.InformacaoService;

@Service
public class InformacaoServiceImpl implements InformacaoService{

	@Override
	public List<Informacao> buscarPorTopicoId(Long topicoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InformacaoDTO toDto(Informacao info) {
		// TODO Auto-generated method stub
		return null;
	}

}
