package com.clinica.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CitaRequest(
        @NotNull(message = "Selecciona un paciente") Long pacienteId,
        @NotNull(message = "Selecciona un médico") Long medicoId,
        @NotNull(message = "Indica la fecha y hora") LocalDateTime fechaHora,
        @NotNull(message = "Indica la duración")
        @Min(value = 10, message = "Mínimo 10 minutos")
        @Max(value = 240, message = "Máximo 240 minutos") Integer duracionMinutos,
        @Size(max = 200, message = "Máximo 200 caracteres") String motivo,
        @Size(max = 500, message = "Máximo 500 caracteres") String notas) {
}
