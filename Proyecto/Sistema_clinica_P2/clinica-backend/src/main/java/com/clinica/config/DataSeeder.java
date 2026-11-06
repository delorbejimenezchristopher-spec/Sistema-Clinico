package com.clinica.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.clinica.model.AccionAuditoria;
import com.clinica.model.Auditoria;
import com.clinica.model.Cita;
import com.clinica.model.Consulta;
import com.clinica.model.EstadoCita;
import com.clinica.model.MedicamentoRecetado;
import com.clinica.model.Medico;
import com.clinica.model.Modulo;
import com.clinica.model.Paciente;
import com.clinica.model.Receta;
import com.clinica.model.Sexo;
import com.clinica.repository.AuditoriaRepository;
import com.clinica.repository.CitaRepository;
import com.clinica.repository.ConsultaRepository;
import com.clinica.repository.MedicoRepository;
import com.clinica.repository.PacienteRepository;
import com.clinica.repository.RecetaRepository;

import lombok.RequiredArgsConstructor;

/**
 * La primera vez que arranca la app (base vacía) crea médicos, pacientes y unos meses
 * de actividad (citas, consultas y recetas) para que todas las pantallas tengan datos.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final CitaRepository citaRepository;
    private final ConsultaRepository consultaRepository;
    private final RecetaRepository recetaRepository;
    private final AuditoriaRepository auditoriaRepository;

    /** Caso clínico típico de cada especialidad. */
    private record Caso(String motivo, String sintomas, String diagnostico, String tratamiento,
            List<MedicamentoRecetado> medicamentos) {
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (medicoRepository.count() > 0 || pacienteRepository.count() > 0) {
            return;
        }
        Random r = new Random(42);
        LocalDate hoy = LocalDate.now();

        List<Medico> medicos = medicoRepository.saveAll(List.of(
                medico("María", "Fernández", "Medicina General", "EXQ-10234", "101", "Lun a Vie 8:00 a 16:00", true),
                medico("José", "Rodríguez", "Cardiología", "EXQ-20871", "204", "Lun, Mié y Vie 9:00 a 13:00", true),
                medico("Laura", "Peña", "Pediatría", "EXQ-31502", "105", "Lun a Sáb 8:00 a 12:00", true),
                medico("Ricardo", "Báez", "Dermatología", "EXQ-44120", "210", "Mar y Jue 14:00 a 18:00", true),
                medico("Elena", "Vargas", "Ginecología", "EXQ-52398", "302", "Lun a Vie 10:00 a 15:00", true),
                medico("Pedro", "Guzmán", "Ortopedia", "EXQ-60917", "115", "Licencia temporal", false)));

        List<Paciente> pacientes = pacienteRepository.saveAll(List.of(
                paciente("001-1234567-1", "Ana", "Martínez", "1988-03-14", Sexo.FEMENINO, "809-555-0101", "O+", "Penicilina", null, "ARS Humano", hoy.minusDays(200)),
                paciente("001-2345678-2", "Carlos", "Pérez", "1965-07-22", Sexo.MASCULINO, "829-555-0202", "A+", null, "Hipertensión arterial diagnosticada en 2015", "ARS Universal", hoy.minusDays(190)),
                paciente("402-3456789-3", "Lucía", "Gómez", "2017-11-02", Sexo.FEMENINO, "849-555-0303", "B+", null, null, "ARS Palic", hoy.minusDays(170)),
                paciente("001-4567890-4", "Miguel", "Santos", "1979-01-30", Sexo.MASCULINO, "809-555-0404", "O-", "Ácaros, polvo", "Asma leve intermitente", null, hoy.minusDays(150)),
                paciente("031-5678901-5", "Sofía", "Reyes", "1993-05-18", Sexo.FEMENINO, "829-555-0505", "A-", null, null, "ARS Humano", hoy.minusDays(130)),
                paciente("001-6789012-6", "Javier", "Núñez", "1958-09-09", Sexo.MASCULINO, "849-555-0606", "AB+", "Sulfas", "Diabetes tipo 2, colesterol alto", "SeNaSa", hoy.minusDays(110)),
                paciente("402-7890123-7", "Valeria", "Castillo", "2015-02-27", Sexo.FEMENINO, "809-555-0707", "O+", null, null, "ARS Universal", hoy.minusDays(90)),
                paciente("001-8901234-8", "Andrés", "Rosario", "1990-12-05", Sexo.MASCULINO, "829-555-0808", "B-", null, null, "ARS Palic", hoy.minusDays(60)),
                paciente("001-9012345-9", "Camila", "Herrera", "2001-08-12", Sexo.FEMENINO, "849-555-0909", "A+", "Ibuprofeno", null, null, hoy.minusDays(3)),
                paciente("223-0123456-0", "Diego", "Almonte", "1972-04-21", Sexo.MASCULINO, "809-555-1010", "O+", null, "Lumbalgia crónica", "SeNaSa", hoy.minusDays(1))));

        List<Auditoria> auditorias = new ArrayList<>();
        medicos.forEach(m -> auditorias.add(auditoria(Modulo.MEDICO, m.getId(), m.nombreCompleto(),
                "Especialidad: " + m.getEspecialidad(), hoy.minusDays(210).atTime(8, 0))));
        pacientes.forEach(p -> auditorias.add(auditoria(Modulo.PACIENTE, p.getId(), p.nombreCompleto(),
                "Cédula: " + p.getCedula(), p.getFechaRegistro().atTime(8, 30))));

        // Historial: una consulta atendida cada ~6 días durante los últimos meses
        List<Medico> activos = medicos.stream().filter(Medico::isActivo).toList();
        for (int i = 0; i < 26; i++) {
            LocalDateTime fecha = hoy.minusDays(i * 6L + 2).atTime(LocalTime.of(8 + r.nextInt(7), r.nextBoolean() ? 0 : 30));
            Medico medico = activos.get(r.nextInt(activos.size()));
            Paciente paciente = medico.getEspecialidad().equals("Pediatría")
                    ? pacientes.get(r.nextBoolean() ? 2 : 6)
                    : pacientes.get(r.nextInt(pacientes.size() - 2));
            if (medico.getEspecialidad().equals("Ginecología") && paciente.getSexo() == Sexo.MASCULINO) {
                medico = activos.get(0);
            }
            Caso caso = caso(medico.getEspecialidad(), r);
            boolean registradoAntes = !paciente.getFechaRegistro().isAfter(fecha.toLocalDate());
            if (!registradoAntes) {
                continue;
            }

            Cita cita = citaRepository.save(Cita.builder().paciente(paciente).medico(medico).fechaHora(fecha)
                    .duracionMinutos(30).motivo(caso.motivo()).estado(EstadoCita.COMPLETADA)
                    .fechaCreacion(fecha.minusDays(3)).build());

            Consulta consulta = consultaRepository.save(Consulta.builder()
                    .paciente(paciente).medico(medico).citaId(cita.getId()).fecha(fecha.plusMinutes(5))
                    .motivo(caso.motivo()).sintomas(caso.sintomas()).diagnostico(caso.diagnostico())
                    .tratamiento(caso.tratamiento())
                    .examenFisico("Paciente consciente, orientado, hidratado. Sin otros hallazgos relevantes.")
                    .presionArterial((110 + r.nextInt(30)) + "/" + (70 + r.nextInt(15)))
                    .temperatura(36.2 + r.nextInt(16) / 10.0)
                    .frecuenciaCardiaca(64 + r.nextInt(30))
                    .pesoKg(paciente.getEdad() < 12 ? 20.0 + r.nextInt(15) : 55.0 + r.nextInt(35))
                    .alturaCm(paciente.getEdad() < 12 ? 110.0 + r.nextInt(25) : 155.0 + r.nextInt(30))
                    .build());
            auditorias.add(auditoria(Modulo.CONSULTA, consulta.getId(), "Consulta de " + paciente.nombreCompleto(),
                    medico.nombreCompleto() + "\nDiagnóstico: " + caso.diagnostico(), consulta.getFecha().plusMinutes(25)));

            if (!caso.medicamentos().isEmpty()) {
                Receta receta = recetaRepository.save(Receta.builder()
                        .paciente(paciente).medico(medico).consultaId(consulta.getId())
                        .fecha(consulta.getFecha().plusMinutes(25)).diagnostico(caso.diagnostico())
                        .indicaciones("Tomar abundante líquido. Volver a consulta si los síntomas persisten.")
                        .medicamentos(new ArrayList<>(caso.medicamentos())).build());
                auditorias.add(auditoria(Modulo.RECETA, receta.getId(), "Receta de " + paciente.nombreCompleto(),
                        medico.nombreCompleto(), receta.getFecha()));
            }
        }

        // Algunas citas pasadas que no se completaron
        citaRepository.save(cita(pacientes.get(3), medicos.get(0), hoy.minusDays(5).atTime(11, 0), "Dolor de cabeza", EstadoCita.CANCELADA));
        citaRepository.save(cita(pacientes.get(7), medicos.get(3), hoy.minusDays(9).atTime(15, 0), "Revisión de lunar", EstadoCita.NO_ASISTIO));

        // Agenda de hoy y de los próximos días
        citaRepository.saveAll(List.of(
                cita(pacientes.get(0), medicos.get(0), hoy.atTime(8, 30), "Control de rutina", EstadoCita.CONFIRMADA),
                cita(pacientes.get(1), medicos.get(1), hoy.atTime(9, 0), "Control de presión arterial", EstadoCita.CONFIRMADA),
                cita(pacientes.get(6), medicos.get(2), hoy.atTime(10, 0), "Fiebre y tos", EstadoCita.PROGRAMADA),
                cita(pacientes.get(4), medicos.get(4), hoy.atTime(11, 30), "Chequeo anual", EstadoCita.PROGRAMADA),
                cita(pacientes.get(8), medicos.get(0), hoy.atTime(14, 0), "Malestar estomacal", EstadoCita.PROGRAMADA),
                cita(pacientes.get(9), medicos.get(0), hoy.atTime(15, 30), "Dolor lumbar", EstadoCita.PROGRAMADA),
                cita(pacientes.get(5), medicos.get(1), hoy.plusDays(1).atTime(9, 30), "Evaluación cardiovascular", EstadoCita.PROGRAMADA),
                cita(pacientes.get(3), medicos.get(0), hoy.plusDays(2).atTime(10, 0), "Seguimiento de asma", EstadoCita.PROGRAMADA),
                cita(pacientes.get(2), medicos.get(2), hoy.plusDays(3).atTime(8, 0), "Control de niño sano", EstadoCita.PROGRAMADA),
                cita(pacientes.get(7), medicos.get(3), hoy.plusDays(5).atTime(14, 30), "Revisión de lunar", EstadoCita.PROGRAMADA),
                cita(pacientes.get(4), medicos.get(4), hoy.plusDays(8).atTime(11, 0), "Resultados de laboratorio", EstadoCita.PROGRAMADA)));

        auditoriaRepository.saveAll(auditorias);
    }

    private static Caso caso(String especialidad, Random r) {
        List<Caso> casos = switch (especialidad) {
            case "Cardiología" -> List.of(
                    new Caso("Control de presión arterial", "Cefalea occipital ocasional", "Hipertensión arterial esencial",
                            "Dieta baja en sodio, ejercicio aeróbico 30 min diarios",
                            List.of(med("Losartán", "50 mg", "Una vez al día", "30 días", "30 tabletas", "Tomar en la mañana"))),
                    new Caso("Palpitaciones", "Sensación de latidos rápidos tras el café", "Taquicardia sinusal",
                            "Reducir cafeína; electrocardiograma de control", List.of()));
            case "Pediatría" -> List.of(
                    new Caso("Tos y congestión nasal", "Rinorrea, tos seca de 3 días", "Rinofaringitis viral",
                            "Lavados nasales con solución salina, abundantes líquidos",
                            List.of(med("Loratadina jarabe", "5 ml", "Una vez al día", "5 días", "1 frasco", null),
                                    med("Acetaminofén jarabe", "7.5 ml", "Cada 6 horas si hay fiebre", "3 días", "1 frasco", "No exceder 4 dosis al día"))),
                    new Caso("Control de niño sano", null, "Crecimiento y desarrollo adecuados",
                            "Continuar esquema de vacunación", List.of()));
            case "Dermatología" -> List.of(
                    new Caso("Erupción en la piel", "Lesiones rojas con picor en antebrazos", "Dermatitis de contacto",
                            "Evitar el contacto con detergentes fuertes",
                            List.of(med("Hidrocortisona crema 1%", "Capa fina", "Dos veces al día", "7 días", "1 tubo", "Aplicar sobre la zona afectada"),
                                    med("Cetirizina", "10 mg", "Una vez al día", "7 días", "7 tabletas", "Tomar en la noche"))),
                    new Caso("Acné", "Comedones y pápulas en rostro", "Acné vulgar leve",
                            "Limpieza facial dos veces al día",
                            List.of(med("Peróxido de benzoilo gel 5%", "Capa fina", "Una vez en la noche", "8 semanas", "1 tubo", null))));
            case "Ginecología" -> List.of(
                    new Caso("Chequeo anual", null, "Control ginecológico normal",
                            "Papanicolaou de rutina; control en 12 meses", List.of()),
                    new Caso("Molestias urinarias", "Ardor al orinar, urgencia", "Infección de vías urinarias no complicada",
                            "Abundantes líquidos",
                            List.of(med("Nitrofurantoína", "100 mg", "Cada 12 horas", "5 días", "10 cápsulas", "Tomar con alimentos"))));
            default -> List.of(
                    new Caso("Fiebre y dolor de garganta", "Odinofagia, fiebre de 38.5 °C", "Faringitis aguda",
                            "Reposo y abundantes líquidos",
                            List.of(med("Amoxicilina", "500 mg", "Cada 8 horas", "7 días", "21 cápsulas", "Completar el tratamiento"),
                                    med("Acetaminofén", "500 mg", "Cada 6 horas si hay fiebre", "5 días", "20 tabletas", null))),
                    new Caso("Dolor de cabeza frecuente", "Cefalea frontal al final del día", "Cefalea tensional",
                            "Higiene del sueño, pausas activas en el trabajo",
                            List.of(med("Naproxeno", "250 mg", "Cada 12 horas", "5 días", "10 tabletas", "Tomar después de comer"))),
                    new Caso("Malestar estomacal", "Ardor epigástrico después de comer", "Gastritis aguda",
                            "Dieta blanda, evitar irritantes y alcohol",
                            List.of(med("Omeprazol", "20 mg", "Una vez al día en ayunas", "14 días", "14 cápsulas", null))));
        };
        return casos.get(r.nextInt(casos.size()));
    }

    private static MedicamentoRecetado med(String nombre, String dosis, String frecuencia, String duracion,
            String cantidad, String indicaciones) {
        return MedicamentoRecetado.builder().medicamento(nombre).dosis(dosis).frecuencia(frecuencia)
                .duracion(duracion).cantidad(cantidad).indicaciones(indicaciones).build();
    }

    private static Medico medico(String nombre, String apellido, String especialidad, String exequatur,
            String consultorio, String horario, boolean activo) {
        String usuario = (nombre.charAt(0) + apellido).toLowerCase()
                .replace('á', 'a').replace('é', 'e').replace('í', 'i').replace('ó', 'o').replace('ú', 'u').replace('ñ', 'n');
        return Medico.builder().nombre(nombre).apellido(apellido).especialidad(especialidad).exequatur(exequatur)
                .consultorio(consultorio).horario(horario).activo(activo)
                .telefono("809-555-2" + exequatur.substring(5, 8)).email(usuario + "@saludintegral.do").build();
    }

    private static Paciente paciente(String cedula, String nombre, String apellido, String nacimiento, Sexo sexo,
            String telefono, String sangre, String alergias, String antecedentes, String seguro, LocalDate registro) {
        return Paciente.builder().cedula(cedula).nombre(nombre).apellido(apellido)
                .fechaNacimiento(LocalDate.parse(nacimiento)).sexo(sexo).telefono(telefono).tipoSangre(sangre)
                .alergias(alergias).antecedentes(antecedentes).seguroMedico(seguro)
                .numeroAfiliado(seguro == null ? null : "AF-" + cedula.substring(4, 11))
                .direccion("Santo Domingo, R.D.").fechaRegistro(registro).build();
    }

    private static Cita cita(Paciente p, Medico m, LocalDateTime fecha, String motivo, EstadoCita estado) {
        return Cita.builder().paciente(p).medico(m).fechaHora(fecha).duracionMinutos(30).motivo(motivo)
                .estado(estado).build();
    }

    private static Auditoria auditoria(Modulo modulo, Long id, String descripcion, String detalle, LocalDateTime fecha) {
        return Auditoria.builder().accion(AccionAuditoria.CREAR).modulo(modulo).registroId(id)
                .descripcion(descripcion).detalle(detalle).fecha(fecha).build();
    }
}
