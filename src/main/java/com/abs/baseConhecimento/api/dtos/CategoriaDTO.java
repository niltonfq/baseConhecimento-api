package com.abs.baseConhecimento.api.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.abs.baseConhecimento.api.entities.Categoria;


public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	
	private List<CategoriaDTO> itens = new ArrayList<>();
	
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		
		adicionarItens(this, obj.getSubs());
		
	}

	private void adicionarItens(CategoriaDTO dto, List<Categoria> lista) {
		for (Categoria categoria : lista) {
			dto.getItens().add(new CategoriaDTO(categoria));
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
	
}
