package com.abs.baseConhecimento.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;
import com.abs.baseConhecimento.api.repositories.InformacaoRepository;
import com.abs.baseConhecimento.api.services.InformacaoService;
import com.abs.baseConhecimento.api.services.TopicoService;

@Service
public class InformacaoServiceImpl implements InformacaoService{

	@Autowired
	private TopicoService topicoService;
	@Autowired
	private InformacaoRepository repo;
	
	@Override
	public List<Informacao> buscarPorTopicoId(Long topicoId) {
		return repo.findByTopicoId(topicoId);
	}

	@Override
	public InformacaoDTO fromInformacaoToDto(Informacao info) {
		return new InformacaoDTO(info.getId(), info.getDescricao());
	}

	@Override
	public Optional<Informacao> find(Long id) {
		return repo.findById(id);
	}

	@Override
	public Informacao save(Informacao informacao) {
		return repo.save(informacao);
	}

	@Override
	public Informacao update(Informacao informacao) {
		return repo.save(informacao);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Informacao fromDtoToInformacao(InformacaoDTO informacaoDTO, BindingResult result) {
		return new Informacao(informacaoDTO.getId(), informacaoDTO.getDescricao(), 
				topicoService.fromDtoToTopico(informacaoDTO.getTopico(), result));
	}

}
