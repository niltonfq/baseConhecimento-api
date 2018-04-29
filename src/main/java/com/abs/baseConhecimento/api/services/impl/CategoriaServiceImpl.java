package com.abs.baseConhecimento.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.repositories.CategoriaRepository;
import com.abs.baseConhecimento.api.repositories.TopicoCategoriaRepository;
import com.abs.baseConhecimento.api.services.CategoriaService;


@Service
public class CategoriaServiceImpl implements CategoriaService{

	//private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

	@Autowired
	private CategoriaRepository repo;
	@Autowired
	private TopicoCategoriaRepository topicoCategoriaRepository;

	@Override
	public List<Categoria> list() {
		List<Categoria> lista = repo.findByParentIsNull();
		for (Categoria categoria : lista) {
			carregaSubs(categoria);
		}
		return lista;
	}

	private void carregaSubs(Categoria pai) {
		
		pai.setSubs(repo.findByParent(pai));
		pai.setTopicoCategoriaList(topicoCategoriaRepository.findByIdCategoria(pai));
		for (Categoria filho : pai.getSubs()) {
			carregaSubs(filho);
		}
	}
	
	
}
