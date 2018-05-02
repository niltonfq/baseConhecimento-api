	package com.abs.baseConhecimento.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;
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
	public Categoria save(Categoria categoria) {
		return repo.save(categoria);
	}

	@Override
	public Optional<Categoria> find(Long id) {
		return repo.findById(id);
	}
	
	@Override
	public Categoria update(Categoria obj) {
		Optional<Categoria> newObj = find(obj.getId());
		if(newObj == null) {
			return null;
		}
		updateData(newObj.get(), obj);
		return repo.save(newObj.get());
	}

	@Override
	public CategoriaDTO fromCategoriaToDto(Categoria obj) {
		CategoriaDTO dto = new CategoriaDTO(obj.getId(), obj.getNome());		
		if(obj.getParent() != null) {
			dto.setPai(obj.getParent().getId());
		}
		return dto;
	}
	
	@Override
	public CategoriaDTO fromCategoriaToDtoComSubsComTopicos(Categoria obj) {
		CategoriaDTO dto = fromCategoriaToDto(obj);
		adicionarTopicos(dto, obj.getTopicoCategoriaList());
		adicionarItens(dto, obj.getSubs());
		return dto;
	}
	

	private void adicionarTopicos(CategoriaDTO dto, List<TopicoCategoria> topicoCategoriaList) {
		
		for (TopicoCategoria topicoCategoria : topicoCategoriaList) {
			TopicoDTO obj = new TopicoDTO(topicoCategoria.getId().getTopico().getId(), 
					topicoCategoria.getId().getTopico().getNome());
			dto.getTopicos().add(obj);
		}
		
	}

	private void adicionarItens(CategoriaDTO dto, List<Categoria> lista) {
		for (Categoria categoria : lista) {
			CategoriaDTO obj = fromCategoriaToDto(categoria);
			adicionarItens(obj, categoria.getSubs());
			adicionarTopicos(obj, categoria.getTopicoCategoriaList());
			dto.getItens().add(obj);
		}
	}
	
	
	@Override
	public Categoria fromDtoToCategoria(CategoriaDTO dto, BindingResult result) {
		
		Categoria cat = new Categoria(dto.getId(), dto.getNome());
		if(dto.getPai() != null) {
			cat.setParent(new Categoria(dto.getPai(), ""));
		}

		return cat;
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		newObj.setParent(obj.getParent());
	}

}
