package com.abs.baseConhecimento.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abs.baseConhecimento.api.entities.Anexo;
import com.abs.baseConhecimento.api.entities.Topico;

public interface AnexoRepository extends JpaRepository<Anexo, Long> {

	List<Anexo> findByTopico(Topico topico);
}
