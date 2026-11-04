package dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto;

import java.math.BigDecimal;

public record TransacaoEstatisticaResponse(
    int quantidade,
    BigDecimal somaTotalTransacao,
    BigDecimal mediaTransacao,
    BigDecimal menorTransacao,
    BigDecimal maiorTransacao
){}
