package br.gov.serpro.atendimento.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.service.EquipeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/equipes")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService service;

    @GetMapping("/ativas")
    public ResponseEntity<List<Map<String, Object>>> listarAtivas(@RequestHeader("cpf") String cpf,
                                                                  @RequestHeader("nome") String nome,
                                                                  @RequestParam("uuidRegioesAtuacao") List<String> regioes) {
        return ResponseEntity.ok(service.listarEquipesAtivas(cpf, nome, regioes));
    }

    @GetMapping("/cpf")
    public ResponseEntity<List<Map<String, Object>>> listarPorCpf(@RequestParam("cpf") String cpf) {
        return ResponseEntity.ok(service.listarEquipesPorCpf(cpf));
    }

    @PostMapping("/localizacoes")
    public ResponseEntity<Void> receberLocalizacoes(@RequestBody Map<String, Object> payload) {
        service.receberLocalizacoes(payload);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Map<String, Object>> detalhar(@RequestHeader("cpf") String cpf,
                                                         @RequestHeader("nome") String nome,
                                                         @PathVariable String uuid) {
        return ResponseEntity.ok(service.detalharEquipe(cpf, nome, uuid));
    }

    @PostMapping("/{uuid}/indicador-agente")
    public ResponseEntity<Void> registrarIndicador(@PathVariable String uuid,
                                                   @RequestBody Map<String, Object> payload) {
        service.registrarIndicadorAgente(uuid, payload);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}/localizacao")
    public ResponseEntity<Void> atualizarLocalizacao(@RequestHeader("cpf") String cpf,
                                                     @RequestHeader("nome") String nome,
                                                     @PathVariable String uuid,
                                                     @RequestBody Map<String, Object> payload) {
        service.atualizarLocalizacao(cpf, nome, uuid, payload);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}/status")
    public ResponseEntity<Void> alterarStatus(@RequestHeader("cpf") String cpf,
                                              @RequestHeader("nome") String nome,
                                              @PathVariable String uuid,
                                              @RequestBody Map<String, Object> payload) {
        service.alterarStatus(cpf, nome, uuid, payload);
        return ResponseEntity.ok().build();
    }
}
