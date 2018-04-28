package com.abs.baseConhecimento.api.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.CategoriaSubCategoria;
import com.abs.baseConhecimento.api.entities.Informacao;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;
import com.abs.baseConhecimento.api.repositories.CategoriaRepository;
import com.abs.baseConhecimento.api.repositories.CategoriaSubCategoriaRepository;
import com.abs.baseConhecimento.api.repositories.InformacaoRepository;
import com.abs.baseConhecimento.api.repositories.TopicoCategoriaRepository;
import com.abs.baseConhecimento.api.repositories.TopicoRepository;


@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CategoriaSubCategoriaRepository categoriaSubCategoriaRepository;
	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private TopicoCategoriaRepository topicoCategoriaRepository;
	@Autowired
	private InformacaoRepository informacaoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(1, "Php");
		Categoria cat2 = new Categoria(2, "Comandos");
		
		CategoriaSubCategoria csc = new CategoriaSubCategoria(cat1, cat2);
		
		Topico top1 = new Topico(1, "echo");
		
		TopicoCategoria tct1 = new TopicoCategoria(top1, cat2);
		
		Informacao inf1 = new Informacao(1, "echo $variavel;", top1);

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		categoriaSubCategoriaRepository.saveAll(Arrays.asList(csc));
		topicoRepository.saveAll(Arrays.asList(top1));
		topicoCategoriaRepository.saveAll(Arrays.asList(tct1));
		informacaoRepository.saveAll(Arrays.asList(inf1));
	}
}
