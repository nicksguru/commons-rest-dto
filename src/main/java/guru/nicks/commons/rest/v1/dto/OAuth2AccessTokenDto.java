package guru.nicks.commons.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

/**
 * OAuth2 access token, as specified in
 * <a href="https://datatracker.ietf.org/doc/html/rfc6750#page-10">RFC 6750</a>.
 */
@Schema(description = "OAuth2 access token, as specified in https://datatracker.ietf.org/doc/html/rfc6750#page-10")
@Builder(toBuilder = true)
public record OAuth2AccessTokenDto(

        @JsonProperty("token_type")
        String tokenType,

        @JsonProperty("expires_in")
        Long expiresInSeconds,

        @JsonProperty("access_token")
        String accessToken) implements Serializable {
}
