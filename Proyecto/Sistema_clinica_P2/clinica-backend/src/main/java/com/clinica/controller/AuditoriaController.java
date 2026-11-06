package com.clinica.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.Auditoria;
import com.clinica.model.Modulo;
import com.clinica.service.AuditoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService service;

    /** Últimos 200 movimientos, opcionalmente filtrados por módulo. */
    @GetMapping
    public List<Auditoria> listar(@RequestParam(required = false) Modulo modulo) {
        return service.listar(modulo);
    }
}
