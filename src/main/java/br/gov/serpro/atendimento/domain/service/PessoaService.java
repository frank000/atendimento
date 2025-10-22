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
public class PessoaService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;

    public List<Map<String, Object>> listarPessoas(String uuidAgencia, String cpf, String nome,
                                                   String nomeFuncional, String registroFuncional) {
        Map<String, Object> request = new HashMap<>();
        request.put("uuidAgencia", uuidAgencia);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("nomeFuncional", nomeFuncional);
        request.put("registroFuncional", registroFuncional);
        return executor.execute("/pessoas", HttpMethod.GET, request,
                () -> client.listarPessoas(uuidAgencia, cpf, nome, nomeFuncional, registroFuncional));
    }

    public Map<String, Object> consultarPorCpf(String cpf) {
        return executor.execute("/pessoas/cpf/" + cpf, HttpMethod.GET, Map.of("cpf", cpf),
                () -> client.consultarPessoa(cpf));
    }
}
