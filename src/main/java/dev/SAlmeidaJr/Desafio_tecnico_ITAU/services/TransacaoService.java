package dev.SAlmeidaJr.Desafio_tecnico_ITAU.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto.TransacaoDto;
import dev.SAlmeidaJr.Desafio_tecnico_ITAU.memory.MemoriaService;

@Service
public class TransacaoService {

    private final MemoriaService armazenamento;

    TransacaoService(MemoriaService armazenamento){
        this.armazenamento = armazenamento;
    }

    public ResponseEntity<Void> create(TransacaoDto body){
        if(body.datahora() == null || body.valor() == null){
            return ResponseEntity.badRequest().build();
        }
        if(OffsetDateTime.now().isAfter(body.datahora())){
            return ResponseEntity.unprocessableContent().build();
        }
        if(body.valor().compareTo(BigDecimal.ZERO)<=0){
            return ResponseEntity.unprocessableContent().build();
        }


        return ResponseEntity.ok().build();
    }
}
