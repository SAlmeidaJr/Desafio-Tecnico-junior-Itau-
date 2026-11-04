package dev.SAlmeidaJr.Desafio_tecnico_ITAU.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto.TransacaoEstatisticaResponse;
import dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto.TransacaoRequestDto;
import dev.SAlmeidaJr.Desafio_tecnico_ITAU.services.TransacaoService;

@RestController
public class Transacoes {

    private final TransacaoService transacaoService;

    Transacoes(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @PostMapping("transacao")
    ResponseEntity<Void> criarTransacao(@RequestBody TransacaoRequestDto body){
        return transacaoService.adicionar(body);
    }

    @DeleteMapping("transacao")
    ResponseEntity<Void> deletarTransacao(){
        transacaoService.deletarTransacao();
        return ResponseEntity.ok().build();
    }

    @GetMapping("estatistica")
    ResponseEntity<TransacaoEstatisticaResponse> estaticasTransacao(@RequestBody TransacaoEstatisticaResponse body){
        return ResponseEntity.ok(transacaoService.estatiscaTransacao());
    }
}
