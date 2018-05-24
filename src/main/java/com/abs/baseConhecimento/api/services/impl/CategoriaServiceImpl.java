package com.abs.baseConhecimento.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;
import com.abs.baseConhecimento.api.repositories.CategoriaRepository;
import com.abs.baseConhecimento.api.services.CategoriaService;
import com.abs.baseConhecimento.api.services.TopicoCategoriaService;
import com.abs.baseConhecimento.api.services.exceptions.ObjectNotFoundException;
import com.abs.baseConhecimento.api.services.exceptions.ViolacaoIntegridadeException;


@Service
public class CategoriaServiceImpl implements CategoriaService{

	//private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

	@Autowired
	private CategoriaRepository repo;
	@Autowired
	private TopicoCategoriaService topicoCategoriaService;

	@Override
	@Transactional
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
	@Transactional
	public Categoria update(Categoria obj) {
		if (obj.getId() == obj.getParent().getId()) {
			throw new ViolacaoIntegridadeException("Categoria não pode pertencer a si mesma.");
		}
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	@Override
	@Transactional
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
		return repo.findByParentIsNullOrderByNome();
	}
	
	@Override
	public CategoriaDTO fromCategoriaToDtoComSubsComTopicos(Categoria obj) {
		CategoriaDTO dto = fromCategoriaToDto(obj);
		adicionarTopicos(dto, topicoCategoriaService.topicosCategoria(obj) );
		adicionarItens(dto, repo.findByParentOrderByNome(obj));
		return dto;
	}
	
	private void adicionarTopicos(CategoriaDTO dto, List<TopicoCategoria> topicoCategoriaList) {
		
		for (TopicoCategoria topicoCategoria : topicoCategoriaList) {
			TopicoDTO obj = new TopicoDTO(
					topicoCategoria.getId().getTopico().getId(), 
					topicoCategoria.getId().getTopico().getNome(), 
					topicoCategoria.getId().getCategoria().getId());
			dto.getTopicos().add(obj);
		}
		
	}

	private void adicionarItens(CategoriaDTO dto, List<Categoria> lista) {
		for (Categoria categoria : lista) {
			CategoriaDTO obj = fromCategoriaToDto(categoria);
			adicionarItens(obj, repo.findByParentOrderByNome(categoria));
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
