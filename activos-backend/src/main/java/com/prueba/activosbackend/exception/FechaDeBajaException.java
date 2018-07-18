package com.prueba.activosbackend.exception;

public class FechaDeBajaException extends Exception{
	public FechaDeBajaException() {
		super();
	}

	public FechaDeBajaException(String message, Throwable cause) {
		super(message, cause);
	}

	public FechaDeBajaException(String message) {
		super(message);
	}
	
	public FechaDeBajaException(Throwable cause) {
		super(cause);
	}
}
