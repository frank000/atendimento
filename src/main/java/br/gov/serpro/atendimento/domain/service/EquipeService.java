package br.gov.serpro.atendimento.domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.gov.serpro.atendimento.external.cad.CadAgenteOperacionalClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipeService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;

    public List<Map<String, Object>> listarEquipesAtivas(String cpf, String nome, List<String> regioesAtuacao) {
        Map<String, Object> request = new HashMap<>();
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidRegioesAtuacao", regioesAtuacao);
        return executor.execute("/equipes/ativas", HttpMethod.GET, request,
                () -> client.listarEquipesAtivas(cpf, nome, regioesAtuacao));
    }

    public List<Map<String, Object>> listarEquipesPorCpf(String cpf) {
        return executor.execute("/equipes/cpf", HttpMethod.GET, Map.of("cpf", cpf),
                () -> client.listarEquipesPorCpf(cpf));
    }

    public void receberLocalizacoes(Map<String, Object> command) {
        executor.executeVoid("/equipes/localizacoes", HttpMethod.POST, Map.of("body", command),
                () -> client.receberLocalizacoes(command));
    }

    public Map<String, Object> detalharEquipe(String cpf, String nome, String uuid) {
        Map<String, Object> request = new HashMap<>();
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuid", uuid);
        return executor.execute("/equipes/" + uuid, HttpMethod.GET, request,
                () -> client.detalharEquipe(cpf, nome, uuid));
    }

    public void registrarIndicadorAgente(String uuid, Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("uuid", uuid);
        request.put("body", command);
        executor.executeVoid("/equipes/" + uuid + "/indicador-agente", HttpMethod.POST, request,
                () -> client.registrarIndicadorAgente(uuid, command));
    }

    public void atualizarLocalizacao(String cpf, String nome, String uuid, Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuid", uuid);
        request.put("body", command);
        executor.executeVoid("/equipes/" + uuid + "/localizacao", HttpMethod.PUT, request,
                () -> client.atualizarLocalizacaoEquipe(cpf, nome, uuid, command));
    }

    public void alterarStatus(String cpf, String nome, String uuid, Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuid", uuid);
        request.put("body", command);
        executor.executeVoid("/equipes/" + uuid + "/status", HttpMethod.PUT, request,
                () -> client.alterarStatusEquipe(cpf, nome, uuid, command));
    }
}
