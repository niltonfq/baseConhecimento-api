package com.abs.baseConhecimento.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.abs.baseConhecimento.api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Transactional(readOnly=true)
	List<Categoria> findByParentIsNull();
	
	@Transactional(readOnly=true)
	List<Categoria> findByParent(Categoria parent);
	
}
