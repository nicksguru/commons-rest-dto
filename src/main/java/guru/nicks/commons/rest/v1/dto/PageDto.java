package guru.nicks.commons.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
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
public record PageDto<T>(

        @Schema(description = "Page metadata")
        Metadata metadata,

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
        Metadata metadata;
        List<T> data;

        // empty page
        if ((source == null) || (source.getSize() == 0)) {
            metadata = new Metadata(0, true, true, 0, 0, 0);
            data = Collections.emptyList();
        }
        // non-empty page
        else {
            data = source.stream()
                    .map(sourceToDtoMapper)
                    .toList();

            metadata = new Metadata(source.getNumber(), source.isFirst(), source.isLast(),
                    source.getNumberOfElements(), source.getTotalElements(), source.getTotalPages());
        }

        return new PageDto<>(metadata, data);
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
    public record Metadata(

            @Schema(description = "Page offset (not number), starts from 0", example = "0")
            int offset,

            @Schema(description = "True if this is the first page", example = "true")
            boolean firstPage,

            @Schema(description = "True if this is the last page", example = "false")
            boolean lastPage,

            @Schema(description = "Number of items on this page", example = "3")
            int pageItems,

            @Schema(description = "Total number of items on all pages", example = "5")
            long totalItems,

            @Schema(description = "Total number of pages", example = "2")
            int totalPages) {
    }

}
