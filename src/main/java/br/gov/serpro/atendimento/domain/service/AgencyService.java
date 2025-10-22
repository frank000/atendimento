package br.gov.serpro.atendimento.domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.gov.serpro.atendimento.domain.mapper.AgencyMapper;
import br.gov.serpro.atendimento.domain.model.Agency;
import br.gov.serpro.atendimento.external.cad.CadAgenteOperacionalClient;
import br.gov.serpro.atendimento.external.cad.dto.AgenciaAgenteCampoRepresentation;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgencyService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;
    private final AgencyMapper mapper;

    public List<Agency> listarAgencias(String uuidEO) {
        Map<String, Object> request = new HashMap<>();
        request.put("uuidEO", uuidEO);
        return executor.execute("/agencias", HttpMethod.GET, request, () -> {
            List<AgenciaAgenteCampoRepresentation> representations = client.listarAgencias(uuidEO);
            return mapper.toDomainList(representations);
        });
    }
}
