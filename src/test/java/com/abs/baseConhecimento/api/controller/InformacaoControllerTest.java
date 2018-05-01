package com.abs.baseConhecimento.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.abs.baseConhecimento.api.entities.Informacao;
import com.abs.baseConhecimento.api.services.InformacaoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class InformacaoControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private InformacaoService informacaoService;
	
	private static final String URL = "/api/informacoes";
	private static final Long ID = 1L;

	@Test
	public void testBuscarPorTopico() throws Exception {
		BDDMockito.given(this.informacaoService.buscarPorTopicoId(ID))
			.willReturn(this.dados());
		
		mvc.perform(MockMvcRequestBuilders.get(URL + "/topico/" + ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testBuscarPorTopicoNaoEncontrado() throws Exception {
		BDDMockito.given(this.informacaoService.buscarPorTopicoId(ID))
		.willReturn(new ArrayList<>());
		
		mvc.perform(MockMvcRequestBuilders.get(URL+ "/topico/" + ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	private List<Informacao> dados() {
		List<Informacao> lista = new ArrayList<>();
		lista.add(new Informacao(1, "primeira descricao", null));
		lista.add(new Informacao(2, "segunda descricao", null));
		return lista;
	}
}
