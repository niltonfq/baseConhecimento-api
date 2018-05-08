	package com.abs.baseConhecimento.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;
import com.abs.baseConhecimento.api.repositories.CategoriaRepository;
import com.abs.baseConhecimento.api.repositories.TopicoCategoriaRepository;
import com.abs.baseConhecimento.api.services.CategoriaService;
import com.abs.baseConhecimento.api.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaServiceImpl implements CategoriaService{

	//private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

	@Autowired
	private CategoriaRepository repo;
	@Autowired
	private TopicoCategoriaRepository topicoCategoriaRepository;

	@Override
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	@Override
	public Categoria find(Long id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	@Override
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	@Override
	public void delete(Long id) {
		find(id);
		repo.deleteById(id);
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		newObj.setParent(obj.getParent());
	}

	@Override
	public List<Categoria> listar() {
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
	public Categoria fromDtoToCategoria(CategoriaDTO dto) {
		
		Categoria cat = new Categoria(dto.getId(), dto.getNome());
		if(dto.getPai() != null) {
			cat.setParent(new Categoria(dto.getPai(), ""));
		}

		return cat;
	}
	
	@Override
	public CategoriaDTO fromCategoriaToDto(Categoria obj) {
		CategoriaDTO dto = new CategoriaDTO(obj.getId(), obj.getNome());		
		if(obj.getParent() != null) {
			dto.setPai(obj.getParent().getId());
			dto.setNomePai(obj.getParent().getNome());
		} else {
			dto.setNomePai("");
		}
		return dto;
	}

	@Override
	public List<Categoria> list() {
		return repo.findAll();
	}
}
