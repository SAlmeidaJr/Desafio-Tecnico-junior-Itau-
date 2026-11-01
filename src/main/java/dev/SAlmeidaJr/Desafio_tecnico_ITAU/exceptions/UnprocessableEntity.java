package dev.SAlmeidaJr.Desafio_tecnico_ITAU.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
public class UnprocessableEntity extends RuntimeException {
    public UnprocessableEntity(String message){ super(message);}
}
