package com.abs.baseConhecimento.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abs.baseConhecimento.api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
