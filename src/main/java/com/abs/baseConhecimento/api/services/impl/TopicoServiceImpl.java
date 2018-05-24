package com.abs.baseConhecimento.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
import com.abs.baseConhecimento.api.dtos.TopicoCategoriaDTO;
import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;
import com.abs.baseConhecimento.api.repositories.CategoriaRepository;
import com.abs.baseConhecimento.api.repositories.TopicoCategoriaRepository;
import com.abs.baseConhecimento.api.repositories.TopicoRepository;
import com.abs.baseConhecimento.api.services.TopicoService;
import com.abs.baseConhecimento.api.services.exceptions.ObjectNotFoundException;
import com.abs.baseConhecimento.api.services.exceptions.ViolacaoIntegridadeException;


@Service
public class TopicoServiceImpl implements TopicoService{

	//private static final Logger log = LoggerFactory.getLogger(TopicoServiceImpl.class);

	@Autowired
	private TopicoRepository repo;
	@Autowired
	private TopicoCategoriaRepository topicoCategoriaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

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
		return new TopicoDTO(topico.getId(), topico.getNome(), null);
	}

	@Override
	public Topico fromDtoToTopico(TopicoDTO topicoDTO) {
		if (topicoDTO == null) return null;
		return new Topico(topicoDTO.getId(), topicoDTO.getNome());
	}

	@Override
	public List<CategoriaDTO> findByIdTopico(Long idTopico) {
		Optional<Topico> top = repo.findById(idTopico);
		List<TopicoCategoria> list = topicoCategoriaRepository.findByIdTopico(top.get());
		List<CategoriaDTO> listDto = list.stream().map(obj -> 
				new CategoriaDTO(obj.getId().getCategoria().getId(), obj.getId().getCategoria().getNome()))
				.collect(Collectors.toList());
		
		return listDto;
	}

	@Override
	public void deleteTopicoCategoria(Long idTopico, Long idCategoria) {
		Optional<Topico> topico = repo.findById(idTopico);
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

		List<TopicoCategoria> list = topicoCategoriaRepository.findByIdTopico(topico.get());
		
		if (list.size() == 1) {
			throw new ViolacaoIntegridadeException("Tópico deve possuir pelo menos 1 categoria");
		}
		
		TopicoCategoria obj = new TopicoCategoria(topico.get(), categoria.get());
		topicoCategoriaRepository.delete(obj);
	}

	@Override
	public TopicoCategoria insertTopicoCategoria(TopicoCategoriaDTO obj) {
		Optional<Topico> topico = repo.findById(obj.getTopico().getId());
		Optional<Categoria> categoria = categoriaRepository.findById(obj.getCategoria().getId());
		
		TopicoCategoria topicoCategoria = new TopicoCategoria(topico.get(), categoria.get());
		return topicoCategoriaRepository.save(topicoCategoria);
	}

	
}

