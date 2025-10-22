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
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.service.EmpenhoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/empenhos")
@RequiredArgsConstructor
public class EmpenhoController {

    private final EmpenhoService service;

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> listarStatus() {
        return ResponseEntity.ok(service.listarStatusEmpenho());
    }

    @PutMapping("/{uuidEmpenho}/status")
    public ResponseEntity<Void> atualizarStatus(@RequestHeader("sinesp-username") String sinespUsername,
                                                @RequestHeader("cpf") String cpf,
                                                @RequestHeader("nome") String nome,
                                                @PathVariable String uuidEmpenho,
                                                @RequestBody Map<String, Object> payload) {
        service.atualizarStatusEmpenho(sinespUsername, cpf, nome, uuidEmpenho, payload);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuidEquipe}")
    public ResponseEntity<List<Map<String, Object>>> listarPorEquipe(@PathVariable String uuidEquipe) {
        return ResponseEntity.ok(service.listarEmpenhosEquipe(uuidEquipe));
    }

    @PostMapping("/{uuidOcorrencia}/empenhar")
    public ResponseEntity<String> empenhar(@RequestHeader("sinesp-username") String sinespUsername,
                                           @RequestHeader("cpf") String cpf,
                                           @RequestHeader("nome") String nome,
                                           @PathVariable String uuidOcorrencia,
                                           @RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(service.empenharUnidade(sinespUsername, cpf, nome, uuidOcorrencia, payload));
    }

    @PutMapping("/{uuidOcorrencia}/{uuidEquipe}/confirmacao-recebimento")
    public ResponseEntity<Void> confirmarRecebimento(@RequestHeader("cpf") String cpf,
                                                     @RequestHeader("nome") String nome,
                                                     @PathVariable String uuidOcorrencia,
                                                     @PathVariable String uuidEquipe,
                                                     @RequestBody Map<String, Object> payload) {
        service.confirmarRecebimento(cpf, nome, uuidOcorrencia, uuidEquipe, payload);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuidOcorrencia}/{uuidEquipe}/liberacao")
    public ResponseEntity<Void> liberarEmpenho(@RequestHeader("sinesp-username") String sinespUsername,
                                               @RequestHeader("cpf") String cpf,
                                               @RequestHeader("nome") String nome,
                                               @PathVariable String uuidOcorrencia,
                                               @PathVariable String uuidEquipe,
                                               @RequestBody Map<String, Object> payload) {
        service.liberarEmpenho(sinespUsername, cpf, nome, uuidOcorrencia, uuidEquipe, payload);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuidUs}/empenhos")
    public ResponseEntity<List<Map<String, Object>>> listarPorUnidade(@PathVariable String uuidUs) {
        return ResponseEntity.ok(service.listarEmpenhosUnidade(uuidUs));
    }
}
