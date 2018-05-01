	package com.abs.baseConhecimento.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
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

	@Override
	public Categoria fromDto(CategoriaDTO categoriaDTO, BindingResult result) {

		Categoria cat = new Categoria(null, categoriaDTO.getNome(), null);
		
		if(categoriaDTO.getPai() != null) {
			Optional<Categoria> parent = repo.findById(categoriaDTO.getPai());
			if (!parent.isPresent()) {
				result.addError(new ObjectError("Categoria Pai", "Categoria pai não encontrada:"+categoriaDTO.getPai()));
			}
			cat.setParent(parent.get());
		}
		return cat;
	}

	@Override
	public Categoria save(Categoria categoria) {
		return repo.save(categoria);
	}

	@Override
	public CategoriaDTO toDto(Categoria categoria) {
		return new CategoriaDTO(categoria);
	}
	
	
}
