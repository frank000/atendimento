package br.gov.serpro.atendimento.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.service.UnidadeServicoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/unidades-servico")
@RequiredArgsConstructor
public class UnidadeServicoController {

    private final UnidadeServicoService service;

    @GetMapping("/{uuid}/cabecalho")
    public ResponseEntity<Map<String, Object>> obterCabecalho(@PathVariable String uuid) {
        return ResponseEntity.ok(service.obterCabecalho(uuid));
    }
}
