package guru.nicks.commons.rest.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

/**
 * Must be {@link Serializable @Serializable} for caching.
 */
@Schema(description = "Audit details")
@Builder(toBuilder = true)
public record AuditDetailsDto(

        DetailsDto createdBy,
        DetailsDto lastModifiedBy) implements Serializable {

    @Builder(toBuilder = true)
    public record DetailsDto(

            String userId,
            String traceId) implements Serializable {
    }

}
