package br.gov.serpro.atendimento.domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.gov.serpro.atendimento.external.cad.CadAgenteOperacionalClient;
import br.gov.serpro.atendimento.external.cad.request.OcorrenciaFilter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final CadAgenteOperacionalClient client;
    private final CadIntegrationExecutor executor;

    public Map<String, Object> consultar(String sinespUsername, OcorrenciaFilter filter) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("filter", filter);
        return executor.execute("/ocorrencias", HttpMethod.GET, request,
                () -> client.consultarOcorrencias(sinespUsername, filter));
    }

    public List<Map<String, Object>> listarMotivosEncerramento() {
        return executor.execute("/ocorrencias/motivos-encerramento", HttpMethod.GET, Map.of(),
                client::listarMotivosEncerramento);
    }

    public List<Map<String, Object>> listarTiposLocal() {
        return executor.execute("/ocorrencias/tipo-local", HttpMethod.GET, Map.of(),
                client::listarTiposLocal);
    }

    public void alterarCodigoOrigem(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                    Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/codigo-na-origem", HttpMethod.PUT, request,
                () -> client.alterarCodigoOrigem(sinespUsername, cpf, nome, uuidOcorrencia, command));
    }

    public void alterarEndereco(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/endereco", HttpMethod.PUT, request,
                () -> client.alterarEnderecoOcorrencia(sinespUsername, cpf, nome, uuidOcorrencia, command));
    }

    public String incluirEnvolvido(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                   Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("body", command);
        return executor.execute("/ocorrencias/" + uuidOcorrencia + "/envolvido", HttpMethod.POST, request,
                () -> client.incluirEnvolvido(sinespUsername, cpf, nome, uuidOcorrencia, command));
    }

    public void alterarEnvolvido(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                 String uuidEnvolvido, Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("uuidEnvolvido", uuidEnvolvido);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/envolvido/" + uuidEnvolvido, HttpMethod.PUT, request,
                () -> client.alterarEnvolvido(sinespUsername, cpf, nome, uuidOcorrencia, uuidEnvolvido, command));
    }

    public void excluirEnvolvido(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                 String uuidEnvolvido, Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("uuidEnvolvido", uuidEnvolvido);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/envolvido/" + uuidEnvolvido, HttpMethod.DELETE, request,
                () -> client.excluirEnvolvido(sinespUsername, cpf, nome, uuidOcorrencia, uuidEnvolvido, command));
    }

    public void finalizarOcorrencia(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                    Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/finalizar", HttpMethod.PUT, request,
                () -> client.finalizarOcorrencia(sinespUsername, cpf, nome, uuidOcorrencia, command));
    }

    public void incluirNatureza(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/natureza", HttpMethod.POST, request,
                () -> client.incluirNatureza(sinespUsername, cpf, nome, uuidOcorrencia, command));
    }

    public void excluirNatureza(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/natureza", HttpMethod.DELETE, request,
                () -> client.excluirNatureza(sinespUsername, cpf, nome, uuidOcorrencia, command));
    }

    public void incluirRelato(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                              Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/relato", HttpMethod.POST, request,
                () -> client.incluirRelato(sinespUsername, cpf, nome, uuidOcorrencia, command));
    }

    public String incluirVeiculo(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                 Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("body", command);
        return executor.execute("/ocorrencias/" + uuidOcorrencia + "/veiculo", HttpMethod.POST, request,
                () -> client.incluirVeiculo(sinespUsername, cpf, nome, uuidOcorrencia, command));
    }

    public void alterarVeiculo(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                String uuidVeiculo, Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("uuidVeiculo", uuidVeiculo);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/veiculo/" + uuidVeiculo, HttpMethod.PUT, request,
                () -> client.alterarVeiculo(sinespUsername, cpf, nome, uuidOcorrencia, uuidVeiculo, command));
    }

    public void excluirVeiculo(String sinespUsername, String cpf, String nome, String uuidOcorrencia,
                                String uuidVeiculo, Map<String, Object> command) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("cpf", cpf);
        request.put("nome", nome);
        request.put("uuidOcorrencia", uuidOcorrencia);
        request.put("uuidVeiculo", uuidVeiculo);
        request.put("body", command);
        executor.executeVoid("/ocorrencias/" + uuidOcorrencia + "/veiculo/" + uuidVeiculo, HttpMethod.DELETE, request,
                () -> client.excluirVeiculo(sinespUsername, cpf, nome, uuidOcorrencia, uuidVeiculo, command));
    }

    public Map<String, Object> detalhar(String sinespUsername, String uuid) {
        Map<String, Object> request = new HashMap<>();
        request.put("sinesp-username", sinespUsername);
        request.put("uuid", uuid);
        return executor.execute("/ocorrencias/" + uuid, HttpMethod.GET, request,
                () -> client.detalharOcorrencia(sinespUsername, uuid));
    }
}
