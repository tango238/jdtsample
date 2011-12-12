package com.plugram.jdt;

public class UnexpectedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public UnexpectedException(Throwable t) {
		super(t);
	}
	
	public UnexpectedException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
	
}
