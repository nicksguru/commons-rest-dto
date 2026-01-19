package guru.nicks.commons.rest.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.data.geo.Point;

import java.io.Serializable;

/**
 * Maps {@link Point}.
 */
@Schema(description = "Point on a sphere")
@Builder(toBuilder = true)
public record GeoPointDto(

        @Schema(description = "Latitude")
        double lat,

        @Schema(description = "Longitude")
        double lon) implements Serializable {
}
