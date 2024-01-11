package com.Steam.TratarErros;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratandoErros {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?>erro404(){
		
		return ResponseEntity.notFound().build();
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>erro400(MethodArgumentNotValidException ex){
		var erro = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erro.stream().map(TratamentoDeErros::new).toList());
	}
	
    
	public record TratamentoDeErros (String campo,String mensagem) {
		
		public TratamentoDeErros(FieldError erros) {
			
			this(erros.getField(),erros.getDefaultMessage());
		}
		
	}
}
