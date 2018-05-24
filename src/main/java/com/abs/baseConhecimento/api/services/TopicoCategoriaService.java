package com.abs.baseConhecimento.api.services;

import java.util.List;

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;

public interface TopicoCategoriaService {

	TopicoCategoria insert(TopicoCategoria obj);

	List<TopicoCategoria> topicosCategoria(Categoria obj);

}
