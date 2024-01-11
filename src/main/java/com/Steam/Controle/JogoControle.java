package com.Steam.Controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Steam.Componente.Mensagem;
import com.Steam.Dto.AtualizarJogo;
import com.Steam.Dto.BuscarJogo;
import com.Steam.Dto.CadastrarJogo;
import com.Steam.Modelo.Jogo;
import com.Steam.Repositorio.JogoRepositorio;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("jogo")
public class JogoControle {
    @Autowired
	private JogoRepositorio repositorio;
    
    @PostMapping("/cadastrar")
    @Transactional
    @Operation(summary = "Rota responsável pelo cadastro de jogos")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Jogo cadastrado com sucesso",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jogo.class)
                )
            }
        ),

        @ApiResponse(
            responseCode = "400", 
            description = "Informação inválida",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Mensagem.class)
                )
            }
        )
    })
    
    
    public ResponseEntity<CadastrarJogo>cadastrar(@RequestBody @Valid CadastrarJogo cadastro,UriComponentsBuilder uriBuilder){
    var cadastrar = new Jogo(cadastro);	
    repositorio.save(cadastrar);
    var uri = uriBuilder.path("/jogo/{id}").buildAndExpand(cadastrar.getId()).toUri();
    return ResponseEntity.created(uri).body(new CadastrarJogo(cadastrar));
    
    
  }
    @GetMapping("/buscar")
    @Operation(summary = "Rota responsável pelo listagem de jogos")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Listagem de jogos",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jogo.class)
                )
            }
        )
    })
    public ResponseEntity<List<BuscarJogo>>buscar(){
    var listar = repositorio.findAll().stream().map(BuscarJogo::new).toList();
    return ResponseEntity.ok(listar);
    	
    }
    
    @GetMapping("/buscarId/{id}")
    @Operation(summary = "Rota responsável por obter um jogo através do id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Jogo encontrado",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jogo.class)
                )
            }
        ),

        @ApiResponse(
            responseCode = "404", 
            description = "Jogo não encontrado",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Mensagem.class)
                )
            }
        )
    })
    public ResponseEntity<BuscarJogo>buscarId(@PathVariable Long id){
    var busca = repositorio.getReferenceById(id);
    return ResponseEntity.ok(new BuscarJogo(busca));
 }
    
    @PutMapping("/atualizar")
    @Transactional
    @Operation(summary = "Rota responsável pela alteração de jogos")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Jogo alterado com sucesso",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jogo.class)
                )
            }
        ),

        @ApiResponse(
            responseCode = "400", 
            description = "Falha ao localizar o alterar jogo",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Mensagem.class)
                )
            }
        )
    })
    public ResponseEntity<AtualizarJogo>atualizar(@RequestBody AtualizarJogo atualizar){
    var atualize = repositorio.getReferenceById(atualizar.id());
    atualize.atualizarJogo(atualizar);
    return ResponseEntity.ok(new AtualizarJogo(atualize));
    }
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Rota responsável pela remoção de jogos")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Jogo removido com sucesso",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Mensagem.class)
                )
            }
        ),

        @ApiResponse(
            responseCode = "400", 
            description = "Falha ao encontrar o jogo ou remover",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Mensagem.class)
                )
            }
        )
    })
    public ResponseEntity<Void>excluir(@PathVariable Long id){
     repositorio.deleteById(id);
     return ResponseEntity.noContent().build();
    }
}