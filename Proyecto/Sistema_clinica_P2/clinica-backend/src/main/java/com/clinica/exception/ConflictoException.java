package com.clinica.exception;

/** Regla de negocio violada (email/cédula duplicada, horario ocupado…) → 409. */
public class ConflictoException extends RuntimeException {

    public ConflictoException(String mensaje) {
        super(mensaje);
    }
}
