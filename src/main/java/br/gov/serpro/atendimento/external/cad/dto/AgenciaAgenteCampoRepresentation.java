package br.gov.serpro.atendimento.external.cad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgenciaAgenteCampoRepresentation {
    private String uuid;
    private String nome;
    private String sigla;
}
