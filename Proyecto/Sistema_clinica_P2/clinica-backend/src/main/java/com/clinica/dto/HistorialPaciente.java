package com.clinica.dto;

import java.util.List;

import com.clinica.model.Cita;
import com.clinica.model.Consulta;
import com.clinica.model.Paciente;
import com.clinica.model.Receta;

/** Expediente completo de un paciente. */
public record HistorialPaciente(
        Paciente paciente,
        List<Consulta> consultas,
        List<Receta> recetas,
        List<Cita> citas) {
}
