package com.abs.baseConhecimento.api.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;


public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	
	private List<CategoriaDTO> itens = new ArrayList<>();
	private List<TopicoDTO> topicos = new ArrayList<>();
	
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		
		adicionarTopicos(this, obj.getTopicoCategoriaList());
		adicionarItens(this, obj.getSubs());
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
			CategoriaDTO obj = new CategoriaDTO(categoria);
			dto.getItens().add(obj);
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<CategoriaDTO> getItens() {
		return itens;
	}

	public void setItens(List<CategoriaDTO> itens) {
		this.itens = itens;
	}

	public List<TopicoDTO> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<TopicoDTO> topicos) {
		this.topicos = topicos;
	}
	
}
