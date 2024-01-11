package com.Steam.Dto;

import com.Steam.Enum.Categoria;
import com.Steam.Modelo.Jogo;

import jakarta.validation.constraints.NotBlank;

public record CadastrarJogo(
		@NotBlank
		String nome,
		@NotBlank
		String desenvolvedora,
		
		String dataLancamento,
		
		Categoria categoria,
		
		Double valor) {

	public CadastrarJogo(Jogo cadastrar) {
	 this(cadastrar.getNome(),cadastrar.getDesenvolvedora(),cadastrar.getDataLancamento(),cadastrar.getCategoria(),cadastrar.getValor());
	}

	

}
