package com.abs.baseConhecimento.api.controller.exceptions;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abs.baseConhecimento.api.response.Response;
import com.abs.baseConhecimento.api.services.exceptions.ObjectNotFoundException;
import com.abs.baseConhecimento.api.services.exceptions.ViolacaoIntegridadeException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Response<Object>> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
		
		Response err = new Response<Object>();
		err.getErrors().add("Não encontrado");
		err.getErrors().add("Path:"+request.getRequestURI());
		err.getErrors().add("Método:"+request.getMethod());
		err.getErrors().add(e.getMessage());
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Response<Object>> dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
		
		Response err = new Response<Object>();
		err.getErrors().add("Erro de integridade de dados");
		err.getErrors().add("Path:"+request.getRequestURI());
		err.getErrors().add("Método:"+request.getMethod());
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Response<Object>> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
		
		Response err = new Response<Object>();
		err.getErrors().add(e.getMessage());
		err.getErrors().add("Path:"+request.getRequestURI());
		err.getErrors().add("Método:"+request.getMethod());
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ViolacaoIntegridadeException.class)
	public ResponseEntity<Response<Object>> ViolacaoIntegridadeException(ViolacaoIntegridadeException e, HttpServletRequest request) {
		
		Response err = new Response<Object>();
		err.getErrors().add(e.getMessage());
		err.getErrors().add("Path:"+request.getRequestURI());
		err.getErrors().add("Método:"+request.getMethod());
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
