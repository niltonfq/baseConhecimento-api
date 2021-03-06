package com.abs.baseConhecimento.api.dtos;

import java.io.Serializable;

public class InformacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private TopicoDTO topico;
	private Long categoriaId;
	
	public InformacaoDTO() {
	}

	public InformacaoDTO(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TopicoDTO getTopico() {
		return topico;
	}

	public void setTopico(TopicoDTO topico) {
		this.topico = topico;
	}
	
}
