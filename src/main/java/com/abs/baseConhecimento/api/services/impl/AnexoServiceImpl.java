	package com.abs.baseConhecimento.api.services.impl;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.entities.Anexo;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.repositories.AnexoRepository;
import com.abs.baseConhecimento.api.services.AnexoService;
import com.abs.baseConhecimento.api.services.TopicoService;
import com.abs.baseConhecimento.api.services.exceptions.ObjectNotFoundException;


@Service
public class AnexoServiceImpl implements AnexoService{

	//private static final Logger log = LoggerFactory.getLogger(AnexoServiceImpl.class);

	@Autowired
	private AnexoRepository repo;
	@Autowired
	private TopicoService topicoService;


	@Override
	public Anexo insert(Anexo obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	@Override
	public Anexo find(Long id) {
		Optional<Anexo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Anexo.class.getName()));
	}
	
	@Override
	public Anexo update(Anexo obj) {
		Anexo newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	@Override
	public void delete(Long id) {
		Anexo obj = find(id);
			
		File file = new File(obj.getCaminho());
    	
		if(!file.delete()){
			throw new ObjectNotFoundException("Objeto não encontrado ou sem acesso para excluí-lo");
		}
    	 
		repo.deleteById(id);
	}

	private void updateData(Anexo newObj, Anexo obj) {
		newObj.setCaminho(obj.getCaminho());
		newObj.setNome(obj.getNome());
	}

	@Override
	public List<Anexo> list() {
		return repo.findAll();
	}

	@Override
	public List<Anexo> findByTopico(Long topicoId) {
		Topico topico = topicoService.find(topicoId);
		return repo.findByTopico(topico);
	}

}
