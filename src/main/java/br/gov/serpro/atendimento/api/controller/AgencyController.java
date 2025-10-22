package br.gov.serpro.atendimento.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.model.Agency;
import br.gov.serpro.atendimento.domain.service.AgencyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/agencias")
@RequiredArgsConstructor
public class AgencyController {

    private final AgencyService service;

    @GetMapping
    public ResponseEntity<List<Agency>> listar(@RequestParam(value = "uuidEO", required = false) String uuidEO) {
        return ResponseEntity.ok(service.listarAgencias(uuidEO));
    }
}
