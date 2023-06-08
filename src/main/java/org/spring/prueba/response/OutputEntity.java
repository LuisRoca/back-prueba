package org.spring.prueba.response;

import static org.spring.prueba.Util.MessageUtil.INTERNALERROR;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class OutputEntity<T> {
	
	@JsonIgnore
	private HttpStatus code;
	private ArrayList<String> messages = new ArrayList<>();
	private Boolean error = false;
	private T data;
	
	public OutputEntity<T> done(Integer code, String message, T data){
		this.code = this.code(code);
		this.messages.add(message);
		this.data = data;
		return this;
	}
	
	public OutputEntity<T> failed(Integer code, String message, T data) {
		this.error = true;
		this.code = this.code(code);
		this.messages.add(message);
		this.data = data;

		return this;
	}
	
	public OutputEntity<T> failed(Integer code, ArrayList<String> message, T data) {
		this.error = true;
		this.code = this.code(code);
		this.messages = message;
		this.data = data;

		return this;
	}
	
	public OutputEntity<T> error() {
		this.error = true;
		this.code = this.code(500);
		this.messages.add(INTERNALERROR.getKey());
		this.data = null;

		return this;
	}
	
	private HttpStatus code(Integer code) {
		HttpStatus status = null;
		switch (code) {
			case 200:
				status = HttpStatus.OK;
				break;
			case 201:
				status = HttpStatus.CREATED;
				break;
			case 404:
				status = HttpStatus.NOT_FOUND;
				break;
			case 400:
				status = HttpStatus.BAD_REQUEST;
				break;
			case 500:
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			case 409:
				status = HttpStatus.CONFLICT;
				break;
		}
		return status;
	}
}
