package com.abs.baseConhecimento.api.controller;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abs.baseConhecimento.api.controller.exceptions.FileNotFoundException;
import com.abs.baseConhecimento.api.entities.Anexo;
import com.abs.baseConhecimento.api.entities.Topico;
import com.abs.baseConhecimento.api.services.AnexoService;
import com.abs.baseConhecimento.api.services.TopicoService;

@RestController
@RequestMapping("/api/anexos")
@CrossOrigin(origins = "*")
public class AnexoController {

//	private static final Logger log = LoggerFactory.getLogger(AnexoController.class);
	private static final String CAMINHO_ARQUIVOS = "/Users/nilton/Documents/projetos/www/baseConhecimento/anexos/";
	
	@Autowired
	private AnexoService service;
	@Autowired
	private TopicoService topicoService;
	
	
	/**
	 * Remove um anexo por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Atualizar os dados de uma anexo.
	 * 
	 * @param id
	 * @param AnexoDTO
	 * @return ResponseEntity<Void>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(
			@PathVariable("id") Long id,
			@Valid @RequestBody Anexo obj) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Buscar um anexo por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Anexo>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Anexo> find(@PathVariable("id") Long id) {
		Anexo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Adicionar um anexo.
	 * 
	 * @param Anexo
	 * @param result
	 * @return ResponseEntity<Void>
	 */
	@PostMapping(value = "/topico/{id}")
	public ResponseEntity<Void> insert(
			@RequestParam("file") MultipartFile file,
			@PathVariable("id") Long id) {
		
		if (file.isEmpty()) {
			throw new FileNotFoundException("Informe o arquivo para upload.");
        }

		Long unico = (new Date()).getTime();
		
		String caminho = CAMINHO_ARQUIVOS + unico;
		if (file.getContentType().equals("application/pdf")) {
			caminho += ".pdf";
        } else if (file.getContentType().equals("image/jpeg")) {
        	caminho += ".jpeg";
        } else {
        	throw new FileNotFoundException("Tipo de arquivo inválido.");
        }
		
		try {
			
            byte[] bytes = file.getBytes();
            Path path = Paths.get(caminho);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
		
        Topico topico = topicoService.find(id);
        Anexo obj = new Anexo(null, file.getOriginalFilename(), caminho, topico);
		obj = service.insert(obj);		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Retornar a listagem de anexos de um tópico.
	 * 
	 * @param none
	 * @return ResponseEntity<Response<List<AnexoDTO>>>
	 */
	@GetMapping(value = "/topico/{topicoId}")
	public ResponseEntity<List<Anexo>> listar(@PathVariable("topicoId") Long topicoId) {
		List<Anexo> list = service.findByTopico(topicoId);
		return ResponseEntity.ok(list);
	}
	
}
