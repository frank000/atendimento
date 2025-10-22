package br.gov.serpro.atendimento.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.gov.serpro.atendimento.domain.model.Agency;
import br.gov.serpro.atendimento.external.cad.dto.AgenciaAgenteCampoRepresentation;

@Mapper(componentModel = "spring")
public interface AgencyMapper {
    Agency toDomain(AgenciaAgenteCampoRepresentation representation);

    List<Agency> toDomainList(List<AgenciaAgenteCampoRepresentation> representations);
}
