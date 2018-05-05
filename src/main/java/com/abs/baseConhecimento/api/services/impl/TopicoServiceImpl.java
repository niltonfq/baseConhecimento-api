	package com.abs.baseConhecimento.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.repositories.TopicoRepository;
import com.abs.baseConhecimento.api.services.TopicoService;
import com.abs.baseConhecimento.api.services.exceptions.ObjectNotFoundException;


@Service
public class TopicoServiceImpl implements TopicoService{

	//private static final Logger log = LoggerFactory.getLogger(TopicoServiceImpl.class);

	@Autowired
	private TopicoRepository repo;

	@Override
	public Topico find(Long id) {
		Optional<Topico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Topico.class.getName()));
	}

	@Override
	public Topico insert(Topico obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	@Override
	public Topico update(Topico obj) {
		Topico newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	@Override
	public void delete(Long id) {
		find(id);
		repo.deleteById(id);
	}

	private void updateData(Topico newObj, Topico obj) {
		newObj.setNome(obj.getNome());
	}
	
	@Override
	public TopicoDTO fromTopicoToDto(Topico topico) {
		if (topico == null) return null;
		return new TopicoDTO(topico.getId(), topico.getNome());
	}

	@Override
	public Topico fromDtoToTopico(TopicoDTO topicoDTO) {
		if (topicoDTO == null) return null;
		return new Topico(topicoDTO.getId(), topicoDTO.getNome());
	}
	
}
