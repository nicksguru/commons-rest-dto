package guru.nicks.commons.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link FieldError}.
 */
@Schema(description = "Error related to a request field")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder(toBuilder = true)
public record FieldErrorDto(

        @Schema(description = "Field name", example = "username")
        String fieldName,

        @Schema(description = "Optional error code, such as 'NotNull', 'NotBlank'", example = "NotBlank")
        String errorCode,

        @Schema(description = "Optional error message", example = "Username must not be blank")
        String errorMessage,

        @Schema(description = "Optional details, e.g. for errorCode=Size it's max. and min. values (in this order)")
        List<Object> arguments) implements Serializable {
}
