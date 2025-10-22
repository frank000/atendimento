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
public class EmpenhoService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;

    public Map<String, Object> listarStatusEmpenho() {
        return executor.execute("/empenhos/status", HttpMethod.GET, Map.of(), client::listarStatusEmpenho);
    }

    public void atualizarStatusEmpenho(String sinespUsername, String cpf, String nome, String uuidEmpenho,
                                       Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidEmpenho", uuidEmpenho);
        request.put("body", command);
        executor.executeVoid("/empenhos/" + uuidEmpenho + "/status", HttpMethod.PUT, request,
                () -> client.atualizarStatusEmpenho(sinespUsername, cpf, nome, uuidEmpenho, command));
    }

    public List<Map<String, Object>> listarEmpenhosEquipe(String uuidEquipe) {
        return executor.execute("/empenhos/" + uuidEquipe, HttpMethod.GET, Map.of("uuidEquipe", uuidEquipe),
                () -> client.listarEmpenhosEquipe(uuidEquipe));
    }

    public String empenharUnidade(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                  Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("body", command);
        return executor.execute("/empenhos/" + uuidOcorrencia + "/empenhar", HttpMethod.POST, request,
                () -> client.empenharUnidade(sinespUsername, cpf, nome, uuidOcorrencia, command));
    }

    public void confirmarRecebimento(String cpf, String nome, String uuidOcorrencia, String uuidEquipe,
                                     Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("uuidEquipe", uuidEquipe);
        request.put("body", command);
        executor.executeVoid("/empenhos/" + uuidOcorrencia + "/" + uuidEquipe + "/confirmacao-recebimento",
                HttpMethod.PUT, request,
                () -> client.confirmarRecebimentoEmpenho(cpf, nome, uuidOcorrencia, uuidEquipe, command));
    }

    public void liberarEmpenho(String sinespUsername, String cpf, String nome, String uuidOcorrencia, String uuidEquipe,
                               Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("uuidEquipe", uuidEquipe);
        request.put("body", command);
        executor.executeVoid("/empenhos/" + uuidOcorrencia + "/" + uuidEquipe + "/liberacao", HttpMethod.PUT, request,
                () -> client.liberarEmpenho(sinespUsername, cpf, nome, uuidOcorrencia, uuidEquipe, command));
    }

    public List<Map<String, Object>> listarEmpenhosUnidade(String uuidUs) {
        return executor.execute("/empenhos/" + uuidUs + "/empenhos", HttpMethod.GET, Map.of("uuidUs", uuidUs),
                () -> client.listarEmpenhosUnidade(uuidUs));
    }
}
