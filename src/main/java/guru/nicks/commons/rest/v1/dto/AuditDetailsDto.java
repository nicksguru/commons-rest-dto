package guru.nicks.commons.rest.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * Must be {@link Serializable @Serializable} for caching.
 */
@Schema(description = "Audit details")
public record AuditDetailsDto(

        DetailsDto createdBy,
        DetailsDto lastModifiedBy) {

    public record DetailsDto(

            String userId,
            String traceId) {
    }

}
