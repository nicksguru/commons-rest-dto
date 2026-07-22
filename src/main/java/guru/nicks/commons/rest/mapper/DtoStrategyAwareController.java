package guru.nicks.commons.rest.mapper;

import java.util.function.BiFunction;

/**
 * Provides uniform methods for mapping between source and DTO.
 *
 * @param <T>   source type
 * @param <S>   mapping strategy type
 * @param <DTO> DTO type
 */
public abstract class DtoStrategyAwareController<T, S, DTO> {

    /**
     * @return mapper from source to DTO
     */
    protected abstract BiFunction<T, S, DTO> getDtoMapper();

    /**
     * Maps source to DTO.
     *
     * @param source          source (such as JPA entity)
     * @param mappingStrategy mapping strategy (affects e.g. which lazy-loaded JPA properties to map and thus load from
     *                        DB)
     * @return DTO
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    protected DTO toDto(T source, S mappingStrategy) {
        DTO dto = getDtoMapper().apply(source, mappingStrategy);
        return dto;
    }

}
