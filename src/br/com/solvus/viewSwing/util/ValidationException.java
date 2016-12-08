package br.com.solvus.viewSwing.util;

public class ValidationException extends Exception{
	
	
	private ValidationError error;

	public ValidationException(ValidationError error) {
		super();
		this.error = error;
	}

	public ValidationError getError() {
		return error;
	}

	public void setError(ValidationError error) {
		this.error = error;
	}
	
	
	

}
