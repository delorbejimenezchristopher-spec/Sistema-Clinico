package com.clinica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.config.ClinicaProperties;

import lombok.RequiredArgsConstructor;

/** Datos generales de la clínica (encabezado de documentos impresos). */
@RestController
@RequestMapping("/api/clinica")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaProperties clinica;

    @GetMapping
    public ClinicaProperties datos() {
        return clinica;
    }
}
