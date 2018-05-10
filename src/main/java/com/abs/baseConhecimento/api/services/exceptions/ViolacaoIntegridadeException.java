package com.abs.baseConhecimento.api.services.exceptions;

public class ViolacaoIntegridadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ViolacaoIntegridadeException(String msg) {
		super(msg);
	}
	
	public ViolacaoIntegridadeException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
