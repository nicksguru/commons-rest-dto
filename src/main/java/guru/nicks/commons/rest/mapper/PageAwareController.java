package guru.nicks.commons.rest.mapper;

import guru.nicks.commons.rest.v1.dto.PageDto;

import org.springframework.data.domain.Page;

import java.util.function.Function;

/**
 * Provides uniform methods for mapping between a page of source objects and a page of DTOs.
 *
 * @param <T>   type of object being managed
 * @param <ID>  object ID type
 * @param <DTO> DTO type
 */
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class PageAwareController<T, ID, DTO> extends DtoAwareController<T, ID, DTO> {

    /**
     * @return mapper from a page of source objects to a page of DTOs
     */
    protected abstract Function<Page<T>, PageDto<DTO>> getPageDtoMapper();

    /**
     * Maps a page of source objects to a page of DTOs.
     *
     * @param page source page (such as JPA entities)
     * @return DTO
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    protected PageDto<DTO> toPageDto(Page<T> page) {
        PageDto<DTO> dto = getPageDtoMapper().apply(page);
        return dto;
    }

}
