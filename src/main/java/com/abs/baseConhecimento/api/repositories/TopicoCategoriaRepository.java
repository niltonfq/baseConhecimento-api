package com.abs.baseConhecimento.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;

public interface TopicoCategoriaRepository extends JpaRepository<TopicoCategoria, Long> {

	List<TopicoCategoria> findByIdCategoria(Categoria categoria);
	List<TopicoCategoria> findByIdTopico(Topico topico);
	TopicoCategoria findByIdCategoriaAndIdTopico(Categoria categoria, Topico topico);
	
}
