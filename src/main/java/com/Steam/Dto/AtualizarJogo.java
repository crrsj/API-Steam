package com.Steam.Dto;

import com.Steam.Enum.Categoria;
import com.Steam.Modelo.Jogo;



public record AtualizarJogo(
		Long id,
		
		String nome,
		
		String desenvolvedora,
		
		String dataLancamento,
		
		Categoria categoria,
		
		Double valor ) {

	public AtualizarJogo(Jogo atualize) {
	this(atualize.getId(),atualize.getNome(),atualize.getDesenvolvedora(),atualize.getDataLancamento(),atualize.getCategoria(),atualize.getValor());
	}
		
		
}		
		
		
		
		
		
		
		
		
	                	 


