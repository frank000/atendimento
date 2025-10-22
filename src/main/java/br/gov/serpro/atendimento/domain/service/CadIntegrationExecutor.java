package br.gov.serpro.atendimento.domain.service;

import java.util.function.Supplier;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CadIntegrationExecutor {

    private final IntegrationLogService logService;

    public <T> T execute(String endpoint, HttpMethod method, Object request, Supplier<T> supplier) {
        try {
            T response = supplier.get();
            logService.registerSuccess(endpoint, method, request, response);
            return response;
        } catch (FeignException ex) {
            logService.registerError(endpoint, method, request, ex.status(), ex.contentUTF8());
            throw ex;
        }
    }

    public void executeVoid(String endpoint, HttpMethod method, Object request, Runnable runnable) {
        execute(endpoint, method, request, () -> {
            runnable.run();
            return null;
        });
    }
}
