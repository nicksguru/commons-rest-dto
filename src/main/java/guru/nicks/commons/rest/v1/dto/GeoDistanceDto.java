package guru.nicks.commons.rest.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Schema(description = "Distance on a sphere")
@Builder(toBuilder = true)
public record GeoDistanceDto(

        @Schema(description = "Distance in meters")
        double meters,

        @Schema(description = "Base point")
        GeoPointDto from) implements Serializable {

    /**
     * Constructs object out of the MongoDB document where {@code $geoNear} returns distance calculated. {@link Map}
     * (Mongo Document's base class) is used to avoid dependency on Mongo.
     *
     * @param doc {@code {meters: 1.00, from: {coordinates: [lon, lat]}}}
     */
    @SuppressWarnings("unchecked")
    public static GeoDistanceDto ofMongoGeoNearDocument(Map<String, ?> doc) {
        double meters = (Double) doc.get("meters");
        Validate.isTrue((meters >= 0), "meters must be non-negative");

        Map<String, ?> fromDoc = (Map<String, ?>) doc.get("from");
        requireNonNull(fromDoc, "from");

        List<Double> coordinates = (List<Double>) fromDoc.get("coordinates");
        requireNonNull(coordinates, "coordinates");
        Validate.isTrue(coordinates.size() == 2, "coordinates must consist of 2 values");

        // in Mongo, lon goes first
        GeoPointDto from = GeoPointDto.builder()
                .lat(coordinates.get(1))
                .lon(coordinates.get(0))
                .build();

        return GeoDistanceDto.builder()
                .meters(meters)
                .from(from)
                .build();

    }

}
