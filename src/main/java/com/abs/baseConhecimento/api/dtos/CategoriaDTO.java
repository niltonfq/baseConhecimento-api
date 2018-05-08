package com.abs.baseConhecimento.api.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Long pai;
	private String nomePai;
	
	private List<CategoriaDTO> itens = new ArrayList<>();
	private List<TopicoDTO> topicos = new ArrayList<>();
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

	public Long getPai() {
		return pai;
	}

	public void setPai(Long pai) {
		this.pai = pai;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	
}
