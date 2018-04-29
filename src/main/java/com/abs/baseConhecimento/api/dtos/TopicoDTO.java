package com.abs.baseConhecimento.api.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TopicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	
	private List<InformacaoDTO> informacoes = new ArrayList<>();

	public TopicoDTO() {
	}
	
	public TopicoDTO(long id, String nome, List<InformacaoDTO> informacoes) {
		this.id = id;
		this.nome = nome;
		this.informacoes = informacoes;
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

	public List<InformacaoDTO> getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(List<InformacaoDTO> informacoes) {
		this.informacoes = informacoes;
	}

}
