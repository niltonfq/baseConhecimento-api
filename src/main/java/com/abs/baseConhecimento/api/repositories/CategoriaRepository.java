package com.abs.baseConhecimento.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.abs.baseConhecimento.api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Transactional(readOnly=true)
	@Query("select cat\n" + 
			"from Categoria cat\n" + 
			"where not exists (select csc from CategoriaSubCategoria csc where csc.id.categoriaSub = cat.id)\n" + 
			"order by cat.nome")
	List<Categoria> listCategoriasPai();
	
}
