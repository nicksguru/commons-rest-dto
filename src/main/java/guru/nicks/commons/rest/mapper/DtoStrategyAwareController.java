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
     * @return default mapping strategy (applied by {@link #toDto(T)})
     */
    protected abstract S getDefaultMappingStrategy();

    /**
     * Maps source to DTO.
     *
     * @param source          source (such as JPA entity)
     * @param mappingStrategy mapping strategy (affects e.g. which lazy-loaded JPA properties to map and thus load from
     *                        DB)
     * @return DTO
     * @see #toDto(Object)
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    protected DTO toDto(T source, S mappingStrategy) {
        DTO dto = getDtoMapper().apply(source, mappingStrategy);
        return dto;
    }

    /**
     * Calls {@link #toDto(Object, Object)} passing {@link #getDefaultMappingStrategy()} as the mapping strategy.
     */
    protected DTO toDto(T source) {
        return toDto(source, getDefaultMappingStrategy());
    }

}
