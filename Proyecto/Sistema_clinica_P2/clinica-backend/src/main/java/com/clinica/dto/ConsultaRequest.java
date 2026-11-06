package com.clinica.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ConsultaRequest(
        @NotNull(message = "Selecciona un paciente") Long pacienteId,
        @NotNull(message = "Selecciona un médico") Long medicoId,
        Long citaId,
        @NotBlank(message = "El motivo es obligatorio")
        @Size(max = 300, message = "Máximo 300 caracteres") String motivo,
        @Size(max = 1000, message = "Máximo 1000 caracteres") String sintomas,
        @Size(max = 1000, message = "Máximo 1000 caracteres") String examenFisico,
        @NotBlank(message = "El diagnóstico es obligatorio")
        @Size(max = 500, message = "Máximo 500 caracteres") String diagnostico,
        @Size(max = 1000, message = "Máximo 1000 caracteres") String tratamiento,
        @Size(max = 1000, message = "Máximo 1000 caracteres") String observaciones,
        @Pattern(regexp = "^$|^\\d{2,3}/\\d{2,3}$", message = "Formato 120/80") String presionArterial,
        @DecimalMin(value = "30", message = "Valor no válido")
        @DecimalMax(value = "45", message = "Valor no válido") Double temperatura,
        @Min(value = 20, message = "Valor no válido")
        @Max(value = 250, message = "Valor no válido") Integer frecuenciaCardiaca,
        @DecimalMin(value = "0.5", message = "Valor no válido")
        @DecimalMax(value = "400", message = "Valor no válido") Double pesoKg,
        @DecimalMin(value = "30", message = "Valor no válido")
        @DecimalMax(value = "250", message = "Valor no válido") Double alturaCm) {
}
