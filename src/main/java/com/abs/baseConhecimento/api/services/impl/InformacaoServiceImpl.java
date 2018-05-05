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
import com.abs.baseConhecimento.api.services.exceptions.ObjectNotFoundException;

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
	public Informacao find(Long id) {
		Optional<Informacao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Informacao.class.getName()));
	}

	@Override
	public Informacao insert(Informacao obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	private void updateData(Informacao novo, Informacao informacao) {
		informacao.setDescricao(novo.getDescricao());
	}

	@Override
	public Informacao update(Informacao obj) {
		Informacao newObj = find(obj.getId());
		updateData(obj, newObj);
		return repo.save(newObj);
	}

	@Override
	public void delete(Long id) {
		find(id);
		repo.deleteById(id);
	}

	@Override
	public Informacao fromDtoToInformacao(InformacaoDTO informacaoDTO, BindingResult result) {
		return new Informacao(informacaoDTO.getId(), informacaoDTO.getDescricao(), 
				topicoService.fromDtoToTopico(informacaoDTO.getTopico(), result));
	}
	
	@Override
	public InformacaoDTO fromInformacaoToDto(Informacao info) {
		return new InformacaoDTO(info.getId(), info.getDescricao());
	}
}
