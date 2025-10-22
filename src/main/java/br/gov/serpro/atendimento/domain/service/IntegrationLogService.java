package br.gov.serpro.atendimento.domain.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.gov.serpro.atendimento.domain.document.IntegrationLog;
import br.gov.serpro.atendimento.domain.repository.IntegrationLogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IntegrationLogService {

    private final IntegrationLogRepository repository;

    public void registerSuccess(String endpoint, HttpMethod method, Object request, Object response) {
        IntegrationLog log = IntegrationLog.builder()
                .endpoint(endpoint)
                .httpMethod(method.name())
                .statusCode(HttpStatus.OK.value())
                .requestPayload(request)
                .responsePayload(response)
                .build();
        repository.save(log);
    }

    public void registerError(String endpoint, HttpMethod method, Object request, int statusCode, Object response) {
        IntegrationLog log = IntegrationLog.builder()
                .endpoint(endpoint)
                .httpMethod(method.name())
                .statusCode(statusCode)
                .requestPayload(request)
                .responsePayload(response)
                .build();
        repository.save(log);
    }
}
