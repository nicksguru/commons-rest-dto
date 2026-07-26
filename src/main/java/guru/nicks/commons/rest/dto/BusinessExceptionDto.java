package guru.nicks.commons.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Application business error.
 */
@Schema(description = "Business exception")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder(toBuilder = true)
public record BusinessExceptionDto(

        @Schema(description = "Error code")
        String errorCode,

        @Schema(description = "Message tailored to caller's locale if possible", example = "Error occurred")
        String message,

        @Schema(description = "HTTP request path (none if it's not REST)", example = "/test/index.html")
        String path,

        @Schema(description = "Errors related to request fields")
        List<FieldErrorDto> fieldErrors,

        @Schema(description = "Current date and time (ISO8601)", example = "2025-01-31T00:00:00Z")
        Instant timestamp,

        @Schema(description = "Trace ID", example = "7bbdd16b4afee4e354ddb2d10c989db7")
        String traceId,

        @Schema(description = "Error details, such as custom HTTP response headers")
        Map<String, Object> details) implements Serializable {

    public BusinessExceptionDto {
        if (timestamp == null) {
            timestamp = Instant.now();
        }
    }

}
