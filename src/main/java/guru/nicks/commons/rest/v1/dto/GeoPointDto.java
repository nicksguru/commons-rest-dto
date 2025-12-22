package guru.nicks.commons.rest.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.geo.Point;

/**
 * Maps {@link Point}.
 */
@Schema(description = "Point on a sphere")
public record GeoPointDto(

        @Schema(description = "latitude")
        double lat,

        @Schema(description = "longitude")
        double lon) {
}
