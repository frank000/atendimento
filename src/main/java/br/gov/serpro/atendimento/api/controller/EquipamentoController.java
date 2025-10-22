package br.gov.serpro.atendimento.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serpro.atendimento.domain.service.EquipamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService service;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listar(@RequestParam("uuidAgencia") String uuidAgencia,
                                                            @RequestParam(value = "placa", required = false) String placa,
                                                            @RequestParam(value = "prefixo", required = false) String prefixo) {
        return ResponseEntity.ok(service.listarEquipamentos(uuidAgencia, placa, prefixo));
    }
}
