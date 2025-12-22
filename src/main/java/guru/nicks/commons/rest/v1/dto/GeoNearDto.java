package guru.nicks.commons.rest.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Wrapper for {@code $geoNear} which returns both documents found and distances.
 *
 * @param <T> embedded object type
 */
@Schema(description = "Distance from the given object")
public record GeoNearDto<T>(

        T object,
        GeoDistanceDto distance) {
}
