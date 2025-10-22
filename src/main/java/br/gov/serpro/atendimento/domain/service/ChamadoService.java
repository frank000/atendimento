package br.gov.serpro.atendimento.domain.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.gov.serpro.atendimento.external.cad.CadAgenteOperacionalClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;

    public Map<String, Object> registrarChamado(String sinespUsername, String cpf, String nome, Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("body", command);
        return executor.execute("/chamados", HttpMethod.POST, request,
                () -> client.registrarChamado(sinespUsername, cpf, nome, command));
    }

    public Map<String, Object> detalharChamado(String sinespUsername, String uuidChamado) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("uuidChamado", uuidChamado);
        return executor.execute("/chamados/" + uuidChamado, HttpMethod.GET, request,
                () -> client.detalharChamado(sinespUsername, uuidChamado));
    }

    public Map<String, Object> complementarChamado(String sinespUsername, String cpf, String nome, String uuidChamado,
                                                   Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidChamado", uuidChamado);
        request.put("body", command);
        return executor.execute("/chamados/" + uuidChamado + "/complementos", HttpMethod.POST, request,
                () -> client.complementarChamado(sinespUsername, cpf, nome, uuidChamado, command));
    }
}
