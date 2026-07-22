package guru.nicks.commons.rest.mapper;

import java.util.function.Function;

/**
 * Provides uniform methods for mapping between source and DTO.
 *
 * @param <T>   source type
 * @param <DTO> DTO type
 */
public abstract class DtoAwareController<T, DTO> {

    /**
     * @return mapper from source to DTO
     */
    protected abstract Function<T, DTO> getDtoMapper();

    /**
     * Maps source to DTO.
     *
     * @param source source (such as JPA entity)
     * @return DTO object
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    protected DTO toDto(T source) {
        DTO dto = getDtoMapper().apply(source);
        return dto;
    }

}
