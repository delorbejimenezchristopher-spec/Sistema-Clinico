package com.clinica.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Datos de la clínica (application.properties → clinica.*), usados en las recetas impresas. */
@ConfigurationProperties(prefix = "clinica")
public record ClinicaProperties(String nombre, String direccion, String telefono, String email, String rnc) {
}
