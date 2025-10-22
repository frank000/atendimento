package br.gov.serpro.atendimento.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.service.PessoaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listar(@RequestParam("uuidAgencia") String uuidAgencia,
                                                            @RequestParam(value = "cpf", required = false) String cpf,
                                                            @RequestParam(value = "nome", required = false) String nome,
                                                            @RequestParam(value = "nomeFuncional", required = false) String nomeFuncional,
                                                            @RequestParam(value = "registroFuncional", required = false) String registroFuncional) {
        return ResponseEntity.ok(service.listarPessoas(uuidAgencia, cpf, nome, nomeFuncional, registroFuncional));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Map<String, Object>> consultarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.consultarPorCpf(cpf));
    }
}
