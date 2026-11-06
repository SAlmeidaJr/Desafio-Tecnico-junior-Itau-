package dev.SAlmeidaJr.Desafio_tecnico_ITAU.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;

import org.springframework.stereotype.Service;

import dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto.EstatisticaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EstatisticaService {

    private final TransacaoService transacaoService;

    public EstatisticaResponse calcularEstatisticasTransacao(int valorIntervalo){
        var intervalo = transacaoService.buscarTransacao(valorIntervalo);

        if(intervalo.isEmpty()){
            log.info("Não existe transações nos ultimos 60 segundos");
            return new EstatisticaResponse(
                0,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO
            );
        }

        var quantidadeTransacao = intervalo.size();

        var somaTotalTransacao = intervalo
            .stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        var mediaTransacao = somaTotalTransacao.divide(
            BigDecimal.valueOf(quantidadeTransacao),
            2,
            RoundingMode.HALF_UP);

        var menorTransacao = Collections.min(intervalo);
        var maiorTransacao = Collections.max(intervalo);


        return new EstatisticaResponse(
            quantidadeTransacao,
            somaTotalTransacao,
            mediaTransacao,
            menorTransacao,
            maiorTransacao);
    }


    public EstatisticaResponse calcularEstatisticasTransacao(){
        return calcularEstatisticasTransacao(60);
    }
}
