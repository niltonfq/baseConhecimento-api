package com.abs.baseConhecimento.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abs.baseConhecimento.api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	List<Categoria> findByParentIsNullOrderByNome();
	List<Categoria> findByParentOrderByNome(Categoria parent);
	
}
