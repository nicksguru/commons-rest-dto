package guru.nicks.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Application business error.
 */
@Schema(description = "Business exception")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Value
@NonFinal
@Jacksonized
@Builder
public class BusinessExceptionDto implements Serializable {

    @Schema(description = "Error code")
    String errorCode;

    @Schema(description = "Message tailored to caller's locale if possible", example = "Error occurred")
    String message;

    @Schema(description = "HTTP request path (none if it's not REST)", example = "/test/index.html")
    String path;

    @Schema(description = "Errors related to request fields")
    List<FieldErrorDto> fieldErrors;

    @Schema(description = "Current date and time (ISO8601)", example = "2025-01-31T00:00:00Z")
    Instant timestamp = Instant.now();

    @Schema(description = "Trace ID", example = "7bbdd16b4afee4e354ddb2d10c989db7")
    String traceId;

    @Schema(description = "Error details, such as custom HTTP response headers")
    Map<String, Object> details;

    /**
     * DTO for {@link FieldError}.
     */
    @Schema(description = "Error related to a request field")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Value
    @NonFinal
    @Jacksonized
    @Builder(toBuilder = true)
    public static class FieldErrorDto implements Serializable {

        @Schema(description = "Field name", example = "username")
        String fieldName;

        /**
         * Binding error code from Spring. Depends on validation type: 'NotEmpty', 'TypeMismatch' etc. Nullable because
         * the original {@link FieldError#getCode()} is nullable.
         */
        @Schema(description = "Error code, such as 'NotNull', 'NotBlank', 'TypeMismatch'", example = "NotBlank")
        String errorCode;

        /**
         * Nullable because the original {@link FieldError#getDefaultMessage()} is nullable.
         */
        @Schema(description = "Optional error message", example = "Username must not be blank")
        String errorMessage;

        /**
         * Corresponds to {@link FieldError#getArguments()}.
         */
        @Schema(description = "Optional details, e.g. for errorCode=Size it's max. and min. values (in this order)")
        List<Object> arguments;

    }

}
