package guru.nicks.commons.rest.mapper;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Provides uniform methods for mapping between source and DTO.
 *
 * @param <T>   source type
 * @param <S>   mapping strategy type
 * @param <DTO> DTO type
 */
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class DtoStrategyAwareController<T, ID, S, DTO> {

    /**
     * Retrieves an object with the given ID by calling {@link #ifExistsAndAccessible(Object, Function)}. This means
     * non-existing and inaccessible objects raise a 'Not Found' exception.
     *
     * @param id object ID
     * @return object
     */
    protected T getIfHasAccess(ID id) {
        return ifExistsAndAccessible(id, Function.identity());
    }

    /**
     * Retrieves an object with the given ID by calling {@link #ifExistsAndAccessible(Object, Function)}. This means
     * non-existing and inaccessible objects raise a 'Not Found' exception. Then, maps the object to a DTO using
     * {@link #toDto(Object, Object)}.
     *
     * @param id              object ID
     * @param mappingStrategy mapping strategy (affects e.g. which lazy-loaded JPA properties to map and thus load from
     *                        DB)
     * @return DTO
     */
    protected DTO getDtoIfExistsAndAccessible(ID id, S mappingStrategy) {
        return ifExistsAndAccessible(id, obj -> toDto(obj, mappingStrategy));
    }

    /**
     * Does the same as {@link #getDtoIfExistsAndAccessible(Object, Object)}, just calls {@link #toDto(Object, Object)}
     * i.e. uses {@link #getDefaultMappingStrategy()}.
     *
     * @param id object ID
     * @return DTO
     */
    protected DTO getDtoIfExistsAndAccessible(ID id) {
        return ifExistsAndAccessible(id, this::toDto);
    }

    /**
     * Invokes {@link #getDtoMapper()}.
     *
     * @param source          source object
     * @param mappingStrategy mapping strategy (affects e.g. which lazy-loaded JPA properties to map and thus load from
     *                        DB)
     * @return DTO
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

    /**
     * @return mapper from source to DTO
     */
    protected abstract BiFunction<T, S, DTO> getDtoMapper();

    /**
     * @return default mapping strategy (applied by {@link #toDto(T)})
     */
    protected abstract S getDefaultMappingStrategy();

    /**
     * Runs a given mapper function for the object with the specified ID.
     * <p>
     * If the object does not exist OR the current user has access to it, a <b>'Not Found' exception should be thrown in
     * both cases</b> - to prevent users from knowing if somebody else's objects exists.
     *
     * @param id  object ID
     * @param <R> mapper result type
     * @return what the mapper returns
     */
    protected abstract <R> R ifExistsAndAccessible(ID id, Function<? super T, R> mapper);

}
