package dev.SAlmeidaJr.Desafio_tecnico_ITAU.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    public void estatiscaTransacao(){
        var dataHoraIntervalo = OffsetDateTime.now().minusSeconds(60);
        var intervalo = armazenamento.listAll()
            .stream()
            .filter( n -> n.getKey().isAfter(dataHoraIntervalo))
            .sorted(Map.Entry.comparingByKey())
            .toList();

        var quantidadeTransacao = intervalo.size();
        var somaMaximaTransacao = intervalo.stream().map(Map.Entry::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        var mediaTransacao = somaMaximaTransacao.divide(BigDecimal.valueOf(quantidadeTransacao));

        var valores = intervalo.stream().map(Map.Entry::getValue).toList();

        // var maiorTransacao = ;
        // var menorTransacao = ;
    }


    public void deletarTransacao(){
        armazenamento.clear();
    }
}
