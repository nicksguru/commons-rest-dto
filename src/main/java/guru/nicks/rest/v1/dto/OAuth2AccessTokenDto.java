package guru.nicks.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 *
 */
@Schema(description = "OAuth2 access token, as specified in https://datatracker.ietf.org/doc/html/rfc6750#page-10")
@Value
@NonFinal
@Jacksonized
@Builder(toBuilder = true)
public class OAuth2AccessTokenDto implements Serializable {

    @JsonProperty("token_type")
    String tokenType;

    @JsonProperty("expires_in")
    Long expiresInSeconds;

    @JsonProperty("access_token")
    String accessToken;

}
