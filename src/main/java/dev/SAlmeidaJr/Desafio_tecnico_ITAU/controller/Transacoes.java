package dev.SAlmeidaJr.Desafio_tecnico_ITAU.controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto.TransacaoDto;
import dev.SAlmeidaJr.Desafio_tecnico_ITAU.services.TransacaoService;

@RestController
public class Transacoes {

    private final TransacaoService transacaoService;

    Transacoes(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    //POST(transacao)
    @PostMapping("transacao")
    ResponseEntity<Void> transacao(@RequestBody TransacaoDto body){
        return transacaoService.create(body);
    }



    //DELETE(transacao)




    //GET(estatisticas)
}
