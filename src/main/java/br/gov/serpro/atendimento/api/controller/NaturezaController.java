package br.gov.serpro.atendimento.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.service.NaturezaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/naturezas")
@RequiredArgsConstructor
public class NaturezaController {

    private final NaturezaService service;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listar(@RequestHeader("sinesp-username") String sinespUsername,
                                                            @RequestParam("uuidAgencia") String uuidAgencia) {
        return ResponseEntity.ok(service.listarNaturezas(sinespUsername, uuidAgencia));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Map<String, Object>> detalhar(@PathVariable String uuid) {
        return ResponseEntity.ok(service.detalharNatureza(uuid));
    }
}
