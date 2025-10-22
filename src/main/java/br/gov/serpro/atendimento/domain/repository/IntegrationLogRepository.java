package br.gov.serpro.atendimento.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.gov.serpro.atendimento.domain.document.IntegrationLog;

public interface IntegrationLogRepository extends MongoRepository<IntegrationLog, String> {
}
