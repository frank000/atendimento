package br.gov.serpro.atendimento.domain.service;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.gov.serpro.atendimento.external.cad.CadAgenteOperacionalClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnidadeServicoService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;

    public Map<String, Object> obterCabecalho(String uuid) {
        return executor.execute("/unidade-servico/" + uuid + "/cabecalho", HttpMethod.GET, Map.of("uuid", uuid),
                () -> client.obterCabecalhoUnidade(uuid));
    }
}
