package dev.SAlmeidaJr.Desafio_tecnico_ITAU.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto.TransacaoRequestDto;
import dev.SAlmeidaJr.Desafio_tecnico_ITAU.services.TransacaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("transacao")
@RequiredArgsConstructor
public class Transacoes {

    private final TransacaoService transacaoService;

    ResponseEntity<Void> criarTransacao(@RequestBody TransacaoRequestDto body){
        return transacaoService.adicionar(body);
    }

    ResponseEntity<Void> deletarTransacao(){
        transacaoService.deletarTransacao();
        return ResponseEntity.ok().build();
    }


}
