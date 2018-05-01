package com.abs.baseConhecimento.api.dtos;

import java.io.Serializable;

public class TopicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	
	public TopicoDTO() {
	}
	
	public TopicoDTO(Long id, String nome) {
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

}
