package com.abs.baseConhecimento.api.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class InformacaoServiceTest {

	@Autowired
	private InformacaoService informacaoService;
	
	@Test
	public void testBuscarPorTopicoId() {
		List<Informacao> lista = informacaoService.buscarPorTopicoId(1L);
		assertFalse(lista.isEmpty());
	}
	
	@Test
	public void testToDto() {
		InformacaoDTO dto = informacaoService.toDto(new Informacao(1L, "descricao", null));
		assertNotNull(dto);
	}
}
