package guru.nicks.commons.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * DTO for {@link Page}. Mimics the same behavior as {@link Page}: {@link Streamable#iterator()},
 * {@link Streamable#forEach(Consumer)}, and {@link Streamable#stream()} are bound to {@link #data()}.
 *
 * @param <T> DTO item type
 */
@Schema(description = "Paginated content")
@JsonIgnoreProperties("empty") // pseudo-property - a method in Streamable
@Builder(toBuilder = true)
public record PageDto<T>(

        @Schema(description = "Page metadata")
        MetadataDto metadata,

        @Schema(description = "Page payload")
        List<T> data) implements Streamable<T> {

    /**
     * Factory method.
     *
     * @param source            source page, can be {@code null}
     * @param sourceToDtoMapper maps each content item (S) to T
     * @param <S>               source item type
     * @param <T>               DTO item type
     * @return new instance
     */
    public static <S, T> PageDto<T> of(@Nullable Page<S> source, Function<? super S, T> sourceToDtoMapper) {
        List<T> data;
        MetadataDto metadata;

        // empty page
        if ((source == null) || (source.getSize() == 0)) {
            data = Collections.emptyList();

            metadata = MetadataDto.builder()
                    .offset(0)
                    .firstPage(true)
                    .lastPage(true)
                    .pageItems(0)
                    .totalItems(0L)
                    .totalPages(0)
                    .build();
        } else {
            data = source.stream()
                    .map(sourceToDtoMapper)
                    .toList();
            metadata = MetadataDto.builder()
                    .offset(source.getNumber())
                    .firstPage(source.isFirst())
                    .lastPage(source.isLast())
                    .pageItems(source.getNumberOfElements())
                    .totalItems(source.getTotalElements())
                    .totalPages(source.getTotalPages())
                    .build();
        }

        return PageDto.<T>builder()
                .data(data)
                .metadata(metadata)
                .build();
    }

    /**
     * @return valid iterator for {@link #data()}, even if {@link #data()} returns {@code null}
     */
    @Nonnull
    @Override
    public Iterator<T> iterator() {
        return (data == null)
                ? Collections.emptyIterator()
                : data.iterator();
    }

    @Schema(title = "Page metadata")
    @Builder(toBuilder = true)
    public record MetadataDto(

            @Schema(description = "Page offset (not number), starts from 0", example = "0")
            Integer offset,

            @Schema(description = "True if this is the first page", example = "true")
            Boolean firstPage,

            @Schema(description = "True if this is the last page", example = "false")
            Boolean lastPage,

            @Schema(description = "Number of items on this page", example = "3")
            Integer pageItems,

            @Schema(description = "Total number of items on all pages", example = "5")
            Long totalItems,

            @Schema(description = "Total number of pages", example = "2")
            Integer totalPages) {
    }

}
