package br.gov.serpro.atendimento.domain.document;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "integration_logs")
public class IntegrationLog {

    @Id
    private String id;

    private String endpoint;

    private String httpMethod;

    private Integer statusCode;

    private Object requestPayload;

    private Object responsePayload;

    @Builder.Default
    private Instant createdAt = Instant.now();
}
