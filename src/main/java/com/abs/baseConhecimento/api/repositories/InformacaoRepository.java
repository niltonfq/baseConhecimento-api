package com.abs.baseConhecimento.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abs.baseConhecimento.api.entities.Informacao;

public interface InformacaoRepository extends JpaRepository<Informacao, Long> {

	List<Informacao> findByTopicoId(Long id);
}
