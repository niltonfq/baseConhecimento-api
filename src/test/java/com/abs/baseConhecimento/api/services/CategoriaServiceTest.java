package com.abs.baseConhecimento.api.services;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.abs.baseConhecimento.api.entities.Categoria;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaServiceTest {

	@Autowired
	private CategoriaService categoriaService;
	
	@Test
	public void testListar() {
		List<Categoria> lista = categoriaService.listar();
		assertFalse(lista.isEmpty());
	}
}
