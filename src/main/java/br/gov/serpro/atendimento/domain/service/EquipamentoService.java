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
public class EquipamentoService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;

    public List<Map<String, Object>> listarEquipamentos(String uuidAgencia, String placa, String prefixo) {
        Map<String, Object> request = new HashMap<>();
        request.put("uuidAgencia", uuidAgencia);
        request.put("placa", placa);
        request.put("prefixo", prefixo);
        return executor.execute("/equipamentos", HttpMethod.GET, request,
                () -> client.listarEquipamentos(uuidAgencia, placa, prefixo));
    }
}
