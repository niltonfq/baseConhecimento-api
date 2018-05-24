package com.abs.baseConhecimento.api.dtos;

import java.io.Serializable;

public class TopicoCategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private TopicoDTO topico;
	private CategoriaDTO categoria;
	
	public TopicoCategoriaDTO() {
	}
	
	public TopicoDTO getTopico() {
		return topico;
	}
	public void setTopico(TopicoDTO topico) {
		this.topico = topico;
	}
	public CategoriaDTO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}
	
	
}
