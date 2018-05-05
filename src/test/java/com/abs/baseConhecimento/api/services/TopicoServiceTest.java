package com.abs.baseConhecimento.api.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.abs.baseConhecimento.api.dtos.TopicoDTO;
import com.abs.baseConhecimento.api.entities.Topico;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TopicoServiceTest {
	
	@Autowired
	private TopicoService service;

	@Test
	public void testFind() {
		Topico topico = service.find(1L);
		assertNotNull(topico);
	}

	@Test
	public void testSave() {
		Topico top = service.insert(umTopico());
		assertNotNull(top.getId());
	}

	private Topico umTopico() {
		return new Topico(null, "xuxa");
	}

	@Test
	public void testFromTopicoToDto() {
		TopicoDTO dto = service.fromTopicoToDto(umTopico());
		assertNotNull(dto);
	}

	@Test
	public void testFromDtoToTopico() {
		Topico top = service.fromDtoToTopico(umTopicoDto(), null);
		assertNotNull(top);
	}

	private TopicoDTO umTopicoDto() {
		return new TopicoDTO(1L, "xuxa");
	}

	
}
