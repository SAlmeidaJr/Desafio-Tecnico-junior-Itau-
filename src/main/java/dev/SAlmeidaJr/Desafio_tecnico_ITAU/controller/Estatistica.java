package dev.SAlmeidaJr.Desafio_tecnico_ITAU.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto.EstatisticaResponse;
import dev.SAlmeidaJr.Desafio_tecnico_ITAU.services.EstatisticaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("estatistica")
@RequiredArgsConstructor
public class Estatistica {

    private final EstatisticaService estatisticaService;

    @GetMapping
    ResponseEntity<EstatisticaResponse> estaticasTransacao(@RequestBody EstatisticaResponse body){
        return ResponseEntity.ok(estatisticaService.calcularEstatisticasTransacao());
    }
}
