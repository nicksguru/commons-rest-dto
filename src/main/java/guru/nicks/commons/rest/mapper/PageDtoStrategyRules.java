package guru.nicks.commons.rest.mapper;

import guru.nicks.commons.rest.v1.dto.PageDto;

import org.springframework.data.domain.Page;

import java.util.function.BiFunction;

/**
 * Provides uniform methods for mapping between a page of source objects and a page of DTOs. All the methods are
 * {@code protected} to avoid exposing them in controllers inadvertently.
 *
 * @param <T>   source (such as JPA entity) type
 * @param <S>   mapping strategy type (affects e.g. which lazy-loaded JPA properties to map and thus load from DB)
 * @param <DTO> DTO type
 */
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class PageDtoStrategyRules<T, ID, S, DTO> extends DtoStrategyRules<T, ID, S, DTO> {

    /**
     * @return mapper from a page of source objects to a page of DTOs
     */
    protected abstract BiFunction<Page<T>, S, PageDto<DTO>> getPageDtoMapper();

    /**
     * Maps a page of source objects to a page of DTOs.
     *
     * @param page            source page (such as JPA entities)
     * @param mappingStrategy mapping strategy (affects e.g. which lazy-loaded JPA properties to map and thus load from
     *                        DB)
     * @return DTO
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    protected PageDto<DTO> toPageDto(Page<T> page, S mappingStrategy) {
        PageDto<DTO> dto = getPageDtoMapper().apply(page, mappingStrategy);
        return dto;
    }

}
