package com.clinica.dto;

import com.clinica.model.EstadoCita;

import jakarta.validation.constraints.NotNull;

public record CambioEstadoRequest(@NotNull(message = "Indica el estado") EstadoCita estado) {
}
