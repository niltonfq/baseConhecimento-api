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

import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.services.CategoriaService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoriaControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CategoriaService categoriaService;
	
	private static final String URL = "/api/categorias";
	
	@Test
	public void testList() throws Exception {
		BDDMockito.given(this.categoriaService.list())
			.willReturn(this.dados());
		
		mvc.perform(MockMvcRequestBuilders.get(URL)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private List<Categoria> dados() {
		List<Categoria> lista = new ArrayList<>();
		lista.add(new Categoria(1, "nome", null));
		lista.add(new Categoria(2, "nome2", null));
		return lista;
	}

	@Test
	public void testListSemCategoriasPai() throws Exception {
		BDDMockito.given(this.categoriaService.list())
			.willReturn(new ArrayList<>());
		
		mvc.perform(MockMvcRequestBuilders.get(URL)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
