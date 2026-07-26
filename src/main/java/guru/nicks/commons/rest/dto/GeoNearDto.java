package guru.nicks.commons.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

/**
 * Wrapper for {@code $geoNear} which returns both documents found and distances.
 *
 * @param <T> embedded object type
 */
@Schema(description = "Distance from the given object")
@Builder(toBuilder = true)
public record GeoNearDto<T>(

        T object,
        GeoDistanceDto distance) implements Serializable {
}
