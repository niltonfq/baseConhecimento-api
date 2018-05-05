	package com.abs.baseConhecimento.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.repositories.TopicoRepository;
import com.abs.baseConhecimento.api.services.TopicoService;


@Service
public class TopicoServiceImpl implements TopicoService{

	//private static final Logger log = LoggerFactory.getLogger(TopicoServiceImpl.class);

	@Autowired
	private TopicoRepository repo;

	@Override
	public Optional<Topico> find(Long id) {
		return repo.findById(id);
	}

	@Override
	public Topico save(Topico topico) {
		return repo.save(topico);
	}

	@Override
	public Topico update(Topico topico) {
		return repo.save(topico);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public TopicoDTO fromTopicoToDto(Topico topico) {
		if (topico == null) return null;
		return new TopicoDTO(topico.getId(), topico.getNome());
	}

	@Override
	public Topico fromDtoToTopico(TopicoDTO topicoDTO, BindingResult result) {
		if (topicoDTO == null) return null;
		return new Topico(topicoDTO.getId(), topicoDTO.getNome());
	}
	
}
