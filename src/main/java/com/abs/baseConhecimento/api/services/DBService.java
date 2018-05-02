package com.abs.baseConhecimento.api.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.baseConhecimento.api.entities.Anexo;
import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.entities.Informacao;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.entities.TopicoCategoria;
import com.abs.baseConhecimento.api.repositories.AnexoRepository;
import com.abs.baseConhecimento.api.repositories.CategoriaRepository;
import com.abs.baseConhecimento.api.repositories.InformacaoRepository;
import com.abs.baseConhecimento.api.repositories.TopicoCategoriaRepository;
import com.abs.baseConhecimento.api.repositories.TopicoRepository;


@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private TopicoCategoriaRepository topicoCategoriaRepository;
	@Autowired
	private InformacaoRepository informacaoRepository;
	@Autowired
	private AnexoRepository anexoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(1L, "Php");
		Categoria cat2 = new Categoria(2L, "Comandos");
		cat2.setParent(cat1);
		
		Topico top1 = new Topico(1L, "echo");
		
		TopicoCategoria tct1 = new TopicoCategoria(top1, cat2);
		
		Informacao inf1 = new Informacao(1L, "echo $variavel;", top1);

		Categoria cat3 = new Categoria(3L, "Linux");
		Categoria cat4 = new Categoria(4L, "CentOS");
		cat4.setParent(cat3);
		
		Categoria cat5 = new Categoria(5L, "Ssh");
		cat5.setParent(cat4);
		Categoria cat6 = new Categoria(6L, "Hardering");
		cat6.setParent(cat4);
		
		Topico top2 = new Topico(2L, "Como retirar o banner do serviço ssh");
		TopicoCategoria tct2 = new TopicoCategoria(top2, cat5);
		TopicoCategoria tct3 = new TopicoCategoria(top2, cat6);
		
		Informacao inf2 = new Informacao(2L, "vim /etc/issue.net...", top2);
		Anexo ane1 = new Anexo(1L, "manual do ssh server", "/var/www/html/manuais/ssh.pdf", top2);
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6));
		topicoRepository.saveAll(Arrays.asList(top1, top2));
		topicoCategoriaRepository.saveAll(Arrays.asList(tct1, tct2, tct3));
		informacaoRepository.saveAll(Arrays.asList(inf1, inf2));
		anexoRepository.saveAll(Arrays.asList(ane1));
	}
}
