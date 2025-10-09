package guru.nicks.rest.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.geo.Point;

import java.io.Serializable;

/**
 * Maps {@link Point}.
 */
@Schema(description = "Point on a sphere")
@Value
@NonFinal
@Jacksonized
@Builder(toBuilder = true)
public class GeoPointDto implements Serializable {

    @Schema(description = "latitude")
    double lat;

    @Schema(description = "longitude")
    double lon;

}
