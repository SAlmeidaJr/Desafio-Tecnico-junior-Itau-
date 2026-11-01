package dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoRequestDto(
    BigDecimal valor,
    OffsetDateTime datahora
) {}
