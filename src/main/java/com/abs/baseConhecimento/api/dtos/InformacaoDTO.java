package com.abs.baseConhecimento.api.dtos;

import java.io.Serializable;

public class InformacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String descricao;
	
	public InformacaoDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
