package org.spring.prueba.exepciones;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class MyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> messages = new ArrayList<>();
	private Integer code;
	private HttpStatus code2;
	
	public MyException(ArrayList<String> messages, Integer code) {
		super();
		this.messages = messages;
		this.code = code;
	}
	
	public MyException(String messages, Integer code) {
		super();
		this.messages.add(messages);
		this.code = code;
	}
	
	public MyException(ArrayList<String> messages, HttpStatus code2) {
		super();
		this.messages = messages;
		this.code2 = code2;
	}
	
	public MyException(String messages, HttpStatus code2) {
		super();
		this.messages.add(messages);
		this.code2 = code2;
	}
}
