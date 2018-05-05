package com.abs.baseConhecimento.api.services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.abs.baseConhecimento.api.dtos.InformacaoDTO;
import com.abs.baseConhecimento.api.entities.Informacao;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.repositories.InformacaoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class InformacaoServiceTest {

	@Autowired
	private InformacaoService service;
	@MockBean
	private InformacaoRepository repo;
	
	@Before
	public void setUp() {
		BDDMockito.given(this.repo.findById(Mockito.anyLong())).willReturn(Optional.of(new Informacao()));
		BDDMockito.given(this.repo.save(umaInformacao())).willReturn(new Informacao());
		BDDMockito.given(this.repo.findByTopicoId(Mockito.anyLong())).willReturn(new ArrayList<>());
	}
	
	@Test
	public void testBuscarPorTopicoId() {
		List<Informacao> lista = service.buscarPorTopicoId(1L);
		assertNotNull(lista);
	}
	
	@Test
	public void testFromInformacaoToDto() {
		InformacaoDTO dto = service.fromInformacaoToDto(new Informacao(1L, "descricao", null));
		assertNotNull(dto);
	}
	
	@Test
	public void testSave() {
		Informacao info = service.update(umaInformacao());
		assertNotNull(info);
	}

	private Informacao umaInformacao() {
		return new Informacao(1L, "xuxa", new Topico(1L, "topico"));
	}

}
