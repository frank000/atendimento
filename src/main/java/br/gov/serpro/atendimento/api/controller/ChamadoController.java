package br.gov.serpro.atendimento.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.service.ChamadoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chamados")
@RequiredArgsConstructor
public class ChamadoController {

    private final ChamadoService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarChamado(@RequestHeader("sinesp-username") String sinespUsername,
                                                                @RequestHeader("cpf") String cpf,
                                                                @RequestHeader("nome") String nome,
                                                                @RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(service.registrarChamado(sinespUsername, cpf, nome, payload));
    }

    @GetMapping("/{uuidChamado}")
    public ResponseEntity<Map<String, Object>> detalharChamado(@RequestHeader("sinesp-username") String sinespUsername,
                                                               @PathVariable String uuidChamado) {
        return ResponseEntity.ok(service.detalharChamado(sinespUsername, uuidChamado));
    }

    @PostMapping("/{uuidChamado}/complementos")
    public ResponseEntity<Map<String, Object>> complementar(@RequestHeader("sinesp-username") String sinespUsername,
                                                            @RequestHeader("cpf") String cpf,
                                                            @RequestHeader("nome") String nome,
                                                            @PathVariable String uuidChamado,
                                                            @RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(service.complementarChamado(sinespUsername, cpf, nome, uuidChamado, payload));
    }
}
