package br.gov.serpro.atendimento.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.gov.serpro.atendimento.external.cad.CadAgenteOperacionalClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegiaoService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;

    public List<Map<String, Object>> listarPorAgencia(String uuidAgencia) {
        return executor.execute("/regioes-atuacao", HttpMethod.GET, Map.of("uuidAgencia", uuidAgencia),
                () -> client.listarRegioesAtuacao(uuidAgencia));
    }
}
