package guru.nicks.rest.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 * Must be {@link Serializable @Serializable} for caching.
 */
@Schema(description = "Audit details")
@Value
@NonFinal
@Jacksonized
@Builder(toBuilder = true)
public class AuditDetailsDto implements Serializable {

    Details createdBy;
    Details lastModifiedBy;

    @Value
    @NonFinal
    @Jacksonized
    @Builder(toBuilder = true)
    public static class Details implements Serializable {

        String userId;
        String traceId;

    }

}
