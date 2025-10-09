package guru.nicks.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * DTO for {@link Page}. Mimics the same behavior as {@link Page}: {@link Streamable#iterator()},
 * {@link Streamable#forEach(Consumer)}, and {@link Streamable#stream()} are bound to {@link #getData()}.
 */
@Schema(description = "Paginated content")
@Value
@NonFinal
@JsonIgnoreProperties("empty") // pseudo-property - a method in Streamable
public class PageDto<T extends Serializable> implements Streamable<T>, Serializable {

    @Schema(description = "Page metadata")
    Metadata metadata;

    @Schema(description = "Page payload")
    List<T> data;

    /**
     * Constructor.
     *
     * @param source            source page, can be {@code null}
     * @param sourceToDtoMapper maps each content item (S) to T
     * @param <S>               source item type
     */
    public <S> PageDto(@Nullable Page<S> source, Function<? super S, T> sourceToDtoMapper) {
        if ((source == null) || (source.getSize() == 0)) {
            metadata = Metadata.builder()
                    .firstPage(true)
                    .lastPage(true)
                    .offset(0)
                    .pageItems(0)
                    .totalItems(0)
                    .totalPages(0)
                    .build();

            data = Collections.emptyList();
            return;
        }

        data = source.stream()
                .map(sourceToDtoMapper)
                .toList();

        metadata = Metadata.builder()
                .firstPage(source.isFirst())
                .lastPage(source.isLast())
                .offset(source.getNumber())
                .pageItems(source.getNumberOfElements())
                .totalItems(source.getTotalElements())
                .totalPages(source.getTotalPages())
                .build();
    }

    /**
     * @return valid iterator for {@link #getData()}, even if {@link #getData()} returns {@code null}
     */
    @Nonnull
    @Override
    public Iterator<T> iterator() {
        return (data == null)
                ? Collections.emptyIterator()
                : data.iterator();
    }

    @Schema(title = "Page metadata")
    @Value
    @NonFinal
    @Jacksonized
    @Builder(toBuilder = true)
    public static class Metadata implements Serializable {

        @Schema(description = "Page offset (not number), starts from 0", example = "0")
        int offset;

        @Schema(description = "True if this is the first page", example = "true")
        boolean firstPage;

        @Schema(description = "True if this is the last page", example = "false")
        boolean lastPage;

        @Schema(description = "Number of items on this page", example = "3")
        int pageItems;

        @Schema(description = "Total number of items on all pages", example = "5")
        long totalItems;

        @Schema(description = "Total number of pages", example = "2")
        int totalPages;

    }

}
