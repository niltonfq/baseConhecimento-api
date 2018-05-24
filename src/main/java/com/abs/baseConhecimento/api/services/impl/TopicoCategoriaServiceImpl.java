package com.abs.baseConhecimento.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;
import com.abs.baseConhecimento.api.repositories.TopicoCategoriaRepository;
import com.abs.baseConhecimento.api.services.TopicoCategoriaService;

@Service
public class TopicoCategoriaServiceImpl implements TopicoCategoriaService{

	//private static final Logger log = LoggerFactory.getLogger(TopicoCategoriaServiceImpl.class);

	@Autowired
	private TopicoCategoriaRepository repo;

	@Override
	public TopicoCategoria insert(TopicoCategoria obj) {
		return repo.save(obj);
	}

	@Override
	public List<TopicoCategoria> topicosCategoria(Categoria obj) {
		return repo.findByIdCategoriaOrderByIdTopicoNome( obj );
	}

}
