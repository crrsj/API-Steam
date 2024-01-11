package com.Steam.Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.Steam.Dto.AtualizarJogo;
import com.Steam.Dto.CadastrarJogo;
import com.Steam.Enum.Categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jogos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class Jogo {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String desenvolvedora;
	private String dataLancamento = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	private Double valor;
	
	public Jogo(@Valid CadastrarJogo cadastro) {
		this.nome = cadastro.nome();
		this.desenvolvedora = cadastro.desenvolvedora();
		this.dataLancamento = cadastro.dataLancamento();
		this.categoria = cadastro.categoria();
		this.valor = cadastro.valor();
	}

	public void atualizarJogo(AtualizarJogo atualizar) {
		if(atualizar.nome()!=null) {
			this.nome = atualizar.nome();
		}
		if(atualizar.desenvolvedora()!=null) {
			this.desenvolvedora = atualizar.desenvolvedora();
		}
		if(atualizar.dataLancamento()!=null) {
			this.dataLancamento = atualizar.dataLancamento();
		}
		if(atualizar.categoria()!=null) {
			this.categoria = atualizar.categoria();
		}
		if(atualizar.valor()!=null) {
			this.valor = atualizar.valor();
		}
	}
}
