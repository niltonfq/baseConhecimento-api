package com.abs.baseConhecimento.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import com.abs.baseConhecimento.api.dtos.CategoriaDTO;
import com.abs.baseConhecimento.api.entities.Categoria;
import com.abs.baseConhecimento.api.services.CategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


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

	@Test
	public void testListarPorId() throws Exception {
		BDDMockito.given(this.categoriaService.find(Mockito.anyLong()))
			.willReturn(new Categoria(1L, "nome"));
		
		BDDMockito.given(this.categoriaService.fromCategoriaToDto(Mockito.any(Categoria.class)))
		.willReturn(new CategoriaDTO(1L, "nome"));
		
		mvc.perform(MockMvcRequestBuilders.get(URL+"/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testListSemCategoriasPai() throws Exception {
		BDDMockito.given(this.categoriaService.list())
		.willReturn(new ArrayList<>());
		
		mvc.perform(MockMvcRequestBuilders.get(URL)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void testCadastrarCategoria() throws Exception {
		
		BDDMockito.given(this.categoriaService.fromDtoToCategoria(Mockito.any(CategoriaDTO.class), Mockito.any(BindingResult.class)))
			.willReturn(new Categoria(1L, "nome"));
		BDDMockito.given(this.categoriaService.fromCategoriaToDto(Mockito.any(Categoria.class)))
			.willReturn(new CategoriaDTO(1L, "nome"));
		BDDMockito.given(this.categoriaService.insert(Mockito.any(Categoria.class)))
			.willReturn(new Categoria(1L, "nome"));
		
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testAlterarCategoria() throws Exception {
		
		BDDMockito.given(this.categoriaService.fromDtoToCategoria(Mockito.any(CategoriaDTO.class), Mockito.any(BindingResult.class)))
			.willReturn(new Categoria(1L, "nome"));
		BDDMockito.given(this.categoriaService.fromCategoriaToDto(Mockito.any(Categoria.class)))
			.willReturn(new CategoriaDTO(1L, "nome"));
		BDDMockito.given(this.categoriaService.update(Mockito.any(Categoria.class)))
			.willReturn(new Categoria(1L, "nome"));
		
		mvc.perform(MockMvcRequestBuilders.put(URL+"/1")
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testExcluirCategoria() throws Exception {
		
		BDDMockito.given(this.categoriaService.find(Mockito.anyLong()))
			.willReturn(new Categoria(0L, "nome"));
		
		mvc.perform(MockMvcRequestBuilders.delete(URL+"/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	private List<Categoria> dados() {
		List<Categoria> lista = new ArrayList<>();
		lista.add(new Categoria(1L, "nome"));
		lista.add(new Categoria(2L, "nome2"));
		return lista;
	}
	
	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		CategoriaDTO dto = new CategoriaDTO(1L, "nomedto");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}
}
