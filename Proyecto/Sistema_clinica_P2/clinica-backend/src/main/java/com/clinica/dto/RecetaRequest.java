package com.clinica.dto;

import java.util.List;

import com.clinica.model.MedicamentoRecetado;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecetaRequest(
        @NotNull(message = "Selecciona un paciente") Long pacienteId,
        @NotNull(message = "Selecciona un médico") Long medicoId,
        Long consultaId,
        @Size(max = 300, message = "Máximo 300 caracteres") String diagnostico,
        @Size(max = 1000, message = "Máximo 1000 caracteres") String indicaciones,
        @NotEmpty(message = "Agrega al menos un medicamento")
        @Size(max = 15, message = "Máximo 15 medicamentos por receta")
        List<@Valid MedicamentoRecetado> medicamentos) {
}
