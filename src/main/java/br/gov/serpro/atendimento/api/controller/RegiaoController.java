package br.gov.serpro.atendimento.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.service.RegiaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/regioes-atuacao")
@RequiredArgsConstructor
public class RegiaoController {

    private final RegiaoService service;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listar(@RequestParam("uuidAgencia") String uuidAgencia) {
        return ResponseEntity.ok(service.listarPorAgencia(uuidAgencia));
    }
}
