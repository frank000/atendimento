package br.gov.serpro.atendimento.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.service.OcorrenciaService;
import br.gov.serpro.atendimento.external.cad.request.OcorrenciaFilter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> consultar(@RequestHeader("sinesp-username") String sinespUsername,
                                                         @ModelAttribute OcorrenciaFilter filter) {
        return ResponseEntity.ok(service.consultar(sinespUsername, filter));
    }

    @GetMapping("/motivos-encerramento")
    public ResponseEntity<List<Map<String, Object>>> listarMotivosEncerramento() {
        return ResponseEntity.ok(service.listarMotivosEncerramento());
    }

    @GetMapping("/tipo-local")
    public ResponseEntity<List<Map<String, Object>>> listarTiposLocal() {
        return ResponseEntity.ok(service.listarTiposLocal());
    }

    @PutMapping("/{uuidOcorrencia}/codigo-na-origem")
    public ResponseEntity<Void> alterarCodigo(@RequestHeader("sinesp-username") String sinespUsername,
                                              @RequestHeader("cpf") String cpf,
                                              @RequestHeader("nome") String nome,
                                              @PathVariable String uuidOcorrencia,
                                              @RequestBody Map<String, Object> payload) {
        service.alterarCodigoOrigem(sinespUsername, cpf, nome, uuidOcorrencia, payload);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuidOcorrencia}/endereco")
    public ResponseEntity<Void> alterarEndereco(@RequestHeader("sinesp-username") String sinespUsername,
                                                @RequestHeader("cpf") String cpf,
                                                @RequestHeader("nome") String nome,
                                                @PathVariable String uuidOcorrencia,
                                                @RequestBody Map<String, Object> payload) {
        service.alterarEndereco(sinespUsername, cpf, nome, uuidOcorrencia, payload);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{uuidOcorrencia}/envolvido")
    public ResponseEntity<String> incluirEnvolvido(@RequestHeader("sinesp-username") String sinespUsername,
                                                   @RequestHeader("cpf") String cpf,
                                                   @RequestHeader("nome") String nome,
                                                   @PathVariable String uuidOcorrencia,
                                                   @RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(service.incluirEnvolvido(sinespUsername, cpf, nome, uuidOcorrencia, payload));
    }

    @PutMapping("/{uuidOcorrencia}/envolvido/{uuidEnvolvido}")
    public ResponseEntity<Void> alterarEnvolvido(@RequestHeader("sinesp-username") String sinespUsername,
                                                 @RequestHeader("cpf") String cpf,
                                                 @RequestHeader("nome") String nome,
                                                 @PathVariable String uuidOcorrencia,
                                                 @PathVariable String uuidEnvolvido,
                                                 @RequestBody Map<String, Object> payload) {
        service.alterarEnvolvido(sinespUsername, cpf, nome, uuidOcorrencia, uuidEnvolvido, payload);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuidOcorrencia}/envolvido/{uuidEnvolvido}")
    public ResponseEntity<Void> excluirEnvolvido(@RequestHeader("sinesp-username") String sinespUsername,
                                                 @RequestHeader("cpf") String cpf,
                                                 @RequestHeader("nome") String nome,
                                                 @PathVariable String uuidOcorrencia,
                                                 @PathVariable String uuidEnvolvido,
                                                 @RequestBody Map<String, Object> payload) {
        service.excluirEnvolvido(sinespUsername, cpf, nome, uuidOcorrencia, uuidEnvolvido, payload);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuidOcorrencia}/finalizar")
    public ResponseEntity<Void> finalizar(@RequestHeader("sinesp-username") String sinespUsername,
                                          @RequestHeader("cpf") String cpf,
                                          @RequestHeader("nome") String nome,
                                          @PathVariable String uuidOcorrencia,
                                          @RequestBody Map<String, Object> payload) {
        service.finalizarOcorrencia(sinespUsername, cpf, nome, uuidOcorrencia, payload);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{uuidOcorrencia}/natureza")
    public ResponseEntity<Void> incluirNatureza(@RequestHeader("sinesp-username") String sinespUsername,
                                                @RequestHeader("cpf") String cpf,
                                                @RequestHeader("nome") String nome,
                                                @PathVariable String uuidOcorrencia,
                                                @RequestBody Map<String, Object> payload) {
        service.incluirNatureza(sinespUsername, cpf, nome, uuidOcorrencia, payload);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuidOcorrencia}/natureza")
    public ResponseEntity<Void> excluirNatureza(@RequestHeader("sinesp-username") String sinespUsername,
                                                @RequestHeader("cpf") String cpf,
                                                @RequestHeader("nome") String nome,
                                                @PathVariable String uuidOcorrencia,
                                                @RequestBody Map<String, Object> payload) {
        service.excluirNatureza(sinespUsername, cpf, nome, uuidOcorrencia, payload);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{uuidOcorrencia}/relato")
    public ResponseEntity<Void> incluirRelato(@RequestHeader("sinesp-username") String sinespUsername,
                                              @RequestHeader("cpf") String cpf,
                                              @RequestHeader("nome") String nome,
                                              @PathVariable String uuidOcorrencia,
                                              @RequestBody Map<String, Object> payload) {
        service.incluirRelato(sinespUsername, cpf, nome, uuidOcorrencia, payload);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{uuidOcorrencia}/veiculo")
    public ResponseEntity<String> incluirVeiculo(@RequestHeader("sinesp-username") String sinespUsername,
                                                 @RequestHeader("cpf") String cpf,
                                                 @RequestHeader("nome") String nome,
                                                 @PathVariable String uuidOcorrencia,
                                                 @RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(service.incluirVeiculo(sinespUsername, cpf, nome, uuidOcorrencia, payload));
    }

    @PutMapping("/{uuidOcorrencia}/veiculo/{uuidVeiculo}")
    public ResponseEntity<Void> alterarVeiculo(@RequestHeader("sinesp-username") String sinespUsername,
                                               @RequestHeader("cpf") String cpf,
                                               @RequestHeader("nome") String nome,
                                               @PathVariable String uuidOcorrencia,
                                               @PathVariable String uuidVeiculo,
                                               @RequestBody Map<String, Object> payload) {
        service.alterarVeiculo(sinespUsername, cpf, nome, uuidOcorrencia, uuidVeiculo, payload);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuidOcorrencia}/veiculo/{uuidVeiculo}")
    public ResponseEntity<Void> excluirVeiculo(@RequestHeader("sinesp-username") String sinespUsername,
                                               @RequestHeader("cpf") String cpf,
                                               @RequestHeader("nome") String nome,
                                               @PathVariable String uuidOcorrencia,
                                               @PathVariable String uuidVeiculo,
                                               @RequestBody Map<String, Object> payload) {
        service.excluirVeiculo(sinespUsername, cpf, nome, uuidOcorrencia, uuidVeiculo, payload);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Map<String, Object>> detalhar(@RequestHeader("sinesp-username") String sinespUsername,
                                                        @PathVariable String uuid) {
        return ResponseEntity.ok(service.detalhar(sinespUsername, uuid));
    }
}
