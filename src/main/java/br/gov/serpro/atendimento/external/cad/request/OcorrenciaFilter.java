package br.gov.serpro.atendimento.external.cad.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OcorrenciaFilter {
    private List<String> uuidAgencia;
    private String protocolo;
    private String uuidEquipe;
    private String uuidPessoaEquipe;
    private String dataInicio;
    private String dataFim;
    private String siglaUF;
    private Integer codIBGEMunicipio;
    private String logradouro;
    private String bairro;
    private String pontoReferencia;
    private String tipoLocal;
    private String latitude;
    private String longitude;
    private Integer raio;
    private List<String> uuidRegioesAtuacao;
    private String uuidNatureza;
    private List<String> situacoes;
    private String envolvidoNome;
    private String envolvidoNomeMae;
    private String envolvidoDataNascimento;
    private String envolvidoCPF;
    private String veiculoPlaca;
}
