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
public class NaturezaService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;

    public List<Map<String, Object>> listarNaturezas(String sinespUsername, String uuidAgencia) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("uuidAgencia", uuidAgencia);
        return executor.execute("/naturezas", HttpMethod.GET, request,
                () -> client.listarNaturezas(sinespUsername, uuidAgencia));
    }

    public Map<String, Object> detalharNatureza(String uuid) {
        return executor.execute("/naturezas/" + uuid, HttpMethod.GET, Map.of("uuid", uuid),
                () -> client.detalharNatureza(uuid));
    }
}
