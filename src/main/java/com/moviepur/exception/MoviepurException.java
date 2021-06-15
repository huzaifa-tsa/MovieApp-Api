package com.moviepur.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class MoviepurException extends Exception {

	private static final long serialVersionUID = 1L;
	private  List<String> errors = new ArrayList<>();
	private  int code = -1;
	private  String developerMessage;
	private  int errorCodeValue = -1;
	public final static int SUCCESSFUL_REQUEST = 200;
	public final static int SUCCESS = 201;
	public final static int NO_CONTENT = 204;
	public final static int BAD_REQUEST = 400;
	public final static int UNAUTHORIZED = 401;
	public final static int FORBIDDEN = 403;
	public final static int NOT_FOUND = 404;
	public final static int NOT_ACCEPTABLE = 406;
	public final static int UNSUPPORTED_MEDIA_TYPE = 415;
	public final static int UNPROCESSABLE_ENTITY = 422;
	public final static int UNIQUE_KEY_CONSTRAINT_VIOLATION = 409;
	public final static int MULTIPLE_RECORDS_FOUND = 409;
	public final static int SERVER_ERROR = 500;
	
	public MoviepurException() {}
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public int getErrorCodeValue() {
		return errorCodeValue;
	}

	public void setErrorCodeValue(int errorCodeValue) {
		this.errorCodeValue = errorCodeValue;
	}

	public MoviepurException(Exception e) {
		super(e.getMessage());
	}

	public MoviepurException(String message) {
		super(message);
	}

	public MoviepurException(int code, String message) {
		super(message);
		this.code = code;
	}
	
	
}
