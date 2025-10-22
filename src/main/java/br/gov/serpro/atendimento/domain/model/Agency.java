package br.gov.serpro.atendimento.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Agency {
    String uuid;
    String nome;
    String sigla;
}
