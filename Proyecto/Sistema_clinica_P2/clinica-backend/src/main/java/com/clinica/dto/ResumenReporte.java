package com.clinica.dto;

import java.util.List;

import com.clinica.model.Cita;

public record ResumenReporte(
        long totalPacientes,
        long pacientesNuevosMes,
        long medicosActivos,
        long citasHoy,
        long consultasMes,
        long recetasMes,
        List<Serie> consultasPorMes,
        List<Serie> citasPorEstado,
        List<Serie> consultasPorEspecialidad,
        List<Serie> diagnosticosFrecuentes,
        List<Cita> agendaHoy) {

    public record Serie(String etiqueta, long total) {
    }
}
