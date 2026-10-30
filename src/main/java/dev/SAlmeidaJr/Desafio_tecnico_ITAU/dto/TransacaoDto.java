package dev.SAlmeidaJr.Desafio_tecnico_ITAU.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoDto(
    BigDecimal valor,
    OffsetDateTime datahora
) {}
