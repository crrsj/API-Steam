package com.Steam.Dto;

import com.Steam.Enum.Categoria;
import com.Steam.Modelo.Jogo;



public record BuscarJogo(
		
		Long id,
		
		String nome,
		
		String desenvolvedora,
		
		String dataLancamento,
		
		Categoria categoria,
		
		Double valor  ) { 
		
	   public BuscarJogo(Jogo listar) {
		   this(listar.getId(),listar.getNome(),listar.getDesenvolvedora(),listar.getDataLancamento(),listar.getCategoria(),listar.getValor());
	   }
		
		             

}
