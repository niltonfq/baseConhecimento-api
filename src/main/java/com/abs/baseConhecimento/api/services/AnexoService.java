package com.abs.baseConhecimento.api.services;

import java.util.List;

import com.abs.baseConhecimento.api.entities.Anexo;

public interface AnexoService {

	List<Anexo> list();
	Anexo find(Long id);
	Anexo insert(Anexo obj);
	Anexo update(Anexo obj);
	void delete(Long id);
	List<Anexo> findByTopico(Long topicoId);

}
