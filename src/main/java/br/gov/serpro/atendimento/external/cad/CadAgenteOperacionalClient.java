package br.gov.serpro.atendimento.external.cad;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.serpro.atendimento.external.cad.dto.AgenciaAgenteCampoRepresentation;
import br.gov.serpro.atendimento.external.cad.request.OcorrenciaFilter;

@FeignClient(name = "cadAgenteOperacionalClient", url = "${cad.api.base-url}")
public interface CadAgenteOperacionalClient {

    @GetMapping("/agencias")
    List<AgenciaAgenteCampoRepresentation> listarAgencias(@RequestParam(value = "uuidEO", required = false) String uuidEO);

    @PostMapping("/chamados")
    Map<String, Object> registrarChamado(@RequestHeader("sinesp-username") String sinespUsername,
                                         @RequestHeader("cpf") String cpf,
                                         @RequestHeader("nome") String nome,
                                         @RequestBody Map<String, Object> command);

    @GetMapping("/chamados/{uuidChamado}")
    Map<String, Object> detalharChamado(@RequestHeader("sinesp-username") String sinespUsername,
                                        @PathVariable("uuidChamado") String uuidChamado);

    @PostMapping("/chamados/{uuidChamado}/complementos")
    Map<String, Object> complementarChamado(@RequestHeader("sinesp-username") String sinespUsername,
                                            @RequestHeader("cpf") String cpf,
                                            @RequestHeader("nome") String nome,
                                            @PathVariable("uuidChamado") String uuidChamado,
                                            @RequestBody Map<String, Object> command);

    @GetMapping("/empenhos/status")
    Map<String, Object> listarStatusEmpenho();

    @PutMapping("/empenhos/{uuidEmpenho}/status")
    void atualizarStatusEmpenho(@RequestHeader("sinesp-username") String sinespUsername,
                                @RequestHeader("cpf") String cpf,
                                @RequestHeader("nome") String nome,
                                @PathVariable("uuidEmpenho") String uuidEmpenho,
                                @RequestBody Map<String, Object> command);

    @GetMapping("/empenhos/{uuidEquipe}")
    List<Map<String, Object>> listarEmpenhosEquipe(@PathVariable("uuidEquipe") String uuidEquipe);

    @PostMapping("/empenhos/{uuidOcorrencia}/empenhar")
    String empenharUnidade(@RequestHeader("sinesp-username") String sinespUsername,
                           @RequestHeader("cpf") String cpf,
                           @RequestHeader("nome") String nome,
                           @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                           @RequestBody Map<String, Object> command);

    @PutMapping("/empenhos/{uuidOcorrencia}/{uuidEquipe}/confirmacao-recebimento")
    void confirmarRecebimentoEmpenho(@RequestHeader("cpf") String cpf,
                                     @RequestHeader("nome") String nome,
                                     @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                                     @PathVariable("uuidEquipe") String uuidEquipe,
                                     @RequestBody Map<String, Object> command);

    @PutMapping("/empenhos/{uuidOcorrencia}/{uuidEquipe}/liberacao")
    void liberarEmpenho(@RequestHeader("sinesp-username") String sinespUsername,
                        @RequestHeader("cpf") String cpf,
                        @RequestHeader("nome") String nome,
                        @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                        @PathVariable("uuidEquipe") String uuidEquipe,
                        @RequestBody Map<String, Object> command);

    @GetMapping("/empenhos/{uuidUs}/empenhos")
    List<Map<String, Object>> listarEmpenhosUnidade(@PathVariable("uuidUs") String uuidUs);

    @GetMapping("/equipamentos")
    List<Map<String, Object>> listarEquipamentos(@RequestParam("uuidAgencia") String uuidAgencia,
                                                 @RequestParam(value = "placa", required = false) String placa,
                                                 @RequestParam(value = "prefixo", required = false) String prefixo);

    @GetMapping("/equipes/ativas")
    List<Map<String, Object>> listarEquipesAtivas(@RequestHeader("cpf") String cpf,
                                                  @RequestHeader("nome") String nome,
                                                  @RequestParam("uuidRegioesAtuacao") List<String> regioesAtuacao);

    @GetMapping("/equipes/cpf")
    List<Map<String, Object>> listarEquipesPorCpf(@RequestParam("cpf") String cpf);

    @PostMapping("/equipes/localizacoes")
    void receberLocalizacoes(@RequestBody Map<String, Object> command);

    @GetMapping("/equipes/{uuid}")
    Map<String, Object> detalharEquipe(@RequestHeader("cpf") String cpf,
                                       @RequestHeader("nome") String nome,
                                       @PathVariable("uuid") String uuid);

    @PostMapping("/equipes/{uuid}/indicador-agente")
    void registrarIndicadorAgente(@PathVariable("uuid") String uuid,
                                  @RequestBody Map<String, Object> command);

    @PutMapping("/equipes/{uuid}/localizacao")
    void atualizarLocalizacaoEquipe(@RequestHeader("cpf") String cpf,
                                    @RequestHeader("nome") String nome,
                                    @PathVariable("uuid") String uuid,
                                    @RequestBody Map<String, Object> command);

    @PutMapping("/equipes/{uuid}/status")
    void alterarStatusEquipe(@RequestHeader("cpf") String cpf,
                              @RequestHeader("nome") String nome,
                              @PathVariable("uuid") String uuid,
                              @RequestBody Map<String, Object> command);

    @GetMapping("/funcoes")
    List<Map<String, Object>> listarFuncoes(@RequestParam("uuidAgencia") String uuidAgencia);

    @GetMapping("/naturezas")
    List<Map<String, Object>> listarNaturezas(@RequestHeader("sinesp-username") String sinespUsername,
                                              @RequestParam("uuidAgencia") String uuidAgencia);

    @GetMapping("/naturezas/{uuid}")
    Map<String, Object> detalharNatureza(@PathVariable("uuid") String uuid);

    @GetMapping("/ocorrencias")
    Map<String, Object> consultarOcorrencias(@RequestHeader("sinesp-username") String sinespUsername,
                                             @SpringQueryMap OcorrenciaFilter filter);

    @GetMapping("/ocorrencias/motivos-encerramento")
    List<Map<String, Object>> listarMotivosEncerramento();

    @GetMapping("/ocorrencias/tipo-local")
    List<Map<String, Object>> listarTiposLocal();

    @PutMapping("/ocorrencias/{uuidOcorrencia}/codigo-na-origem")
    void alterarCodigoOrigem(@RequestHeader("sinesp-username") String sinespUsername,
                             @RequestHeader("cpf") String cpf,
                             @RequestHeader("nome") String nome,
                             @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                             @RequestBody Map<String, Object> command);

    @PutMapping("/ocorrencias/{uuidOcorrencia}/endereco")
    void alterarEnderecoOcorrencia(@RequestHeader("sinesp-username") String sinespUsername,
                                   @RequestHeader("cpf") String cpf,
                                   @RequestHeader("nome") String nome,
                                   @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                                   @RequestBody Map<String, Object> command);

    @PostMapping("/ocorrencias/{uuidOcorrencia}/envolvido")
    String incluirEnvolvido(@RequestHeader("sinesp-username") String sinespUsername,
                             @RequestHeader("cpf") String cpf,
                             @RequestHeader("nome") String nome,
                             @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                             @RequestBody Map<String, Object> command);

    @PutMapping("/ocorrencias/{uuidOcorrencia}/envolvido/{uuidEnvolvido}")
    void alterarEnvolvido(@RequestHeader("sinesp-username") String sinespUsername,
                           @RequestHeader("cpf") String cpf,
                           @RequestHeader("nome") String nome,
                           @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                           @PathVariable("uuidEnvolvido") String uuidEnvolvido,
                           @RequestBody Map<String, Object> command);

    @DeleteMapping("/ocorrencias/{uuidOcorrencia}/envolvido/{uuidEnvolvido}")
    void excluirEnvolvido(@RequestHeader("sinesp-username") String sinespUsername,
                           @RequestHeader("cpf") String cpf,
                           @RequestHeader("nome") String nome,
                           @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                           @PathVariable("uuidEnvolvido") String uuidEnvolvido,
                           @RequestBody Map<String, Object> command);

    @PutMapping("/ocorrencias/{uuidOcorrencia}/finalizar")
    void finalizarOcorrencia(@RequestHeader("sinesp-username") String sinespUsername,
                             @RequestHeader("cpf") String cpf,
                             @RequestHeader("nome") String nome,
                             @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                             @RequestBody Map<String, Object> command);

    @PostMapping("/ocorrencias/{uuidOcorrencia}/natureza")
    void incluirNatureza(@RequestHeader("sinesp-username") String sinespUsername,
                         @RequestHeader("cpf") String cpf,
                         @RequestHeader("nome") String nome,
                         @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                         @RequestBody Map<String, Object> command);

    @DeleteMapping("/ocorrencias/{uuidOcorrencia}/natureza")
    void excluirNatureza(@RequestHeader("sinesp-username") String sinespUsername,
                         @RequestHeader("cpf") String cpf,
                         @RequestHeader("nome") String nome,
                         @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                         @RequestBody Map<String, Object> command);

    @PostMapping("/ocorrencias/{uuidOcorrencia}/relato")
    void incluirRelato(@RequestHeader("sinesp-username") String sinespUsername,
                       @RequestHeader("cpf") String cpf,
                       @RequestHeader("nome") String nome,
                       @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                       @RequestBody Map<String, Object> command);

    @PostMapping("/ocorrencias/{uuidOcorrencia}/veiculo")
    String incluirVeiculo(@RequestHeader("sinesp-username") String sinespUsername,
                           @RequestHeader("cpf") String cpf,
                           @RequestHeader("nome") String nome,
                           @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                           @RequestBody Map<String, Object> command);

    @PutMapping("/ocorrencias/{uuidOcorrencia}/veiculo/{uuidVeiculo}")
    void alterarVeiculo(@RequestHeader("sinesp-username") String sinespUsername,
                        @RequestHeader("cpf") String cpf,
                        @RequestHeader("nome") String nome,
                        @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                        @PathVariable("uuidVeiculo") String uuidVeiculo,
                        @RequestBody Map<String, Object> command);

    @DeleteMapping("/ocorrencias/{uuidOcorrencia}/veiculo/{uuidVeiculo}")
    void excluirVeiculo(@RequestHeader("sinesp-username") String sinespUsername,
                        @RequestHeader("cpf") String cpf,
                        @RequestHeader("nome") String nome,
                        @PathVariable("uuidOcorrencia") String uuidOcorrencia,
                        @PathVariable("uuidVeiculo") String uuidVeiculo,
                        @RequestBody Map<String, Object> command);

    @GetMapping("/ocorrencias/{uuid}")
    Map<String, Object> detalharOcorrencia(@RequestHeader("sinesp-username") String sinespUsername,
                                           @PathVariable("uuid") String uuid);

    @GetMapping("/pessoas")
    List<Map<String, Object>> listarPessoas(@RequestParam("uuidAgencia") String uuidAgencia,
                                            @RequestParam(value = "cpf", required = false) String cpf,
                                            @RequestParam(value = "nome", required = false) String nome,
                                            @RequestParam(value = "nomeFuncional", required = false) String nomeFuncional,
                                            @RequestParam(value = "registroFuncional", required = false) String registroFuncional);

    @GetMapping("/pessoas/cpf/{cpf}")
    Map<String, Object> consultarPessoa(@PathVariable("cpf") String cpf);

    @GetMapping("/regioes-atuacao")
    List<Map<String, Object>> listarRegioesAtuacao(@RequestParam("uuidAgencia") String uuidAgencia);

    @GetMapping("/unidade-servico/{uuid}/cabecalho")
    Map<String, Object> obterCabecalhoUnidade(@PathVariable("uuid") String uuid);
}
