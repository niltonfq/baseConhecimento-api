package com.abs.baseConhecimento.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class InformacaoController {

	private static final Logger log = LoggerFactory.getLogger(InformacaoController.class);
	
	
}
