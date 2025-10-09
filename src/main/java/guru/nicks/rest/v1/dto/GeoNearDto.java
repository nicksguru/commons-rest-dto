package guru.nicks.rest.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Wrapper for {@code $geoNear} results which return both documents found and distances.
 *
 * @param <T> embedded object type
 */
@Schema(description = "Distance from the given object")
@Value
@NonFinal
@Jacksonized
@Builder(toBuilder = true)
public class GeoNearDto<T> implements Serializable {

    T object;
    GeoDistanceDto distance;

    @Schema(description = "Distance on a sphere")
    @Value
    @NonFinal
    @Jacksonized
    @Builder(toBuilder = true)
    @AllArgsConstructor
    public static class GeoDistanceDto implements Serializable {

        @Schema(description = "Distance in meters")
        double meters;

        /**
         * {@link #object} may have multiple location fields - this one disambiguates them
         */
        @Schema(description = "Base point")
        GeoPointDto from;

        /**
         * Constructs object out of the conventional document where {@code $geoNear} returns distance calculated. Use
         * {@link Map} (Mongo Document's base class) in order to avoid dependency on Mongo.
         *
         * @param doc {@code {meters: 1.00, from: {coordinates: [lon, lat]}}}
         */
        @SuppressWarnings("unchecked")
        public GeoDistanceDto(Map<String, ?> doc) {
            meters = (Double) doc.get("meters");
            Validate.isTrue((meters >= 0), "meters must be non-negative");

            Map<String, ?> fromDoc = (Map<String, ?>) doc.get("from");
            requireNonNull(fromDoc, "from");

            List<Double> coordinates = (List<Double>) fromDoc.get("coordinates");
            requireNonNull(coordinates, "coordinates");
            Validate.isTrue(coordinates.size() == 2, "coordinates must consist of 2 values");

            // in Mongo, lon always goes first, lat goes next
            from = GeoPointDto.builder()
                    .lat(coordinates.get(1))
                    .lon(coordinates.get(0))
                    .build();
        }

    }

}
