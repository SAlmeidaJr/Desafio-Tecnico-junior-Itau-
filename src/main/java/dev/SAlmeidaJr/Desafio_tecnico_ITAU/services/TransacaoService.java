package dev.SAlmeidaJr.Desafio_tecnico_ITAU.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto.EstatisticaResponse;
import dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto.TransacaoRequestDto;
import dev.SAlmeidaJr.Desafio_tecnico_ITAU.exceptions.UnprocessableEntity;
import dev.SAlmeidaJr.Desafio_tecnico_ITAU.memory.MemoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final MemoriaService armazenamento;

    public ResponseEntity<Void> adicionar(TransacaoRequestDto body){

        log.info("Iniciando o processamento de gravar transação");

        if(body.datahora() == null || body.valor() == null){
            return ResponseEntity.badRequest().build();
        }

        if(OffsetDateTime.now().isAfter(body.datahora())){
            log.info("Data e hora maiores que a data atual");
            throw new UnprocessableEntity("Data e hora maiores que data e hora atual");
        }

        if(body.valor().compareTo(BigDecimal.ZERO)<=0){
            log.info("Valor não poder menor que 0");
            throw new UnprocessableEntity("Valor não poder menor que 0");
        }

        armazenamento.save(body.datahora(), body.valor());

        return ResponseEntity.ok().build();
    }


    public List<BigDecimal> buscarTransacao(int valorIntervalo){
        var dataHoraIntervalo = OffsetDateTime.now().minusSeconds(valorIntervalo);
        return armazenamento.listAll()
            .stream()
            .filter( n -> n.getKey().isAfter(dataHoraIntervalo))
            .map(Map.Entry::getValue)
            .toList();
    }


    public void deletarTransacao(){
        armazenamento.clear();
    }

}
