package guru.nicks.commons.rest.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Delegator class for {@link DtoStrategyRules} that delegates all method calls to a provided delegate instance.
 *
 * @param <T>   source type
 * @param <ID>  object ID type
 * @param <S>   mapping strategy type
 * @param <DTO> DTO type
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class DtoStrategyDelegator<T, ID, S, DTO>
        extends DtoStrategyRules<T, ID, S, DTO> {

    /**
     * Delegate instance to which all method calls are forwarded.
     */
    protected final DtoStrategyRules<T, ID, S, DTO> delegate;

    /**
     * Delegates to {@link DtoStrategyRules#getIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return object
     */
    @Override
    protected T getIfExistsAndAccessible(ID id) {
        return delegate.getIfExistsAndAccessible(id);
    }

    /**
     * Delegates to {@link DtoStrategyRules#getDtoIfExistsAndAccessible(Object, Object)}.
     *
     * @param id              object ID
     * @param mappingStrategy mapping strategy
     * @return DTO
     */
    @Override
    protected DTO getDtoIfExistsAndAccessible(ID id, S mappingStrategy) {
        return delegate.getDtoIfExistsAndAccessible(id, mappingStrategy);
    }

    /**
     * Delegates to {@link DtoStrategyRules#getDtoIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return DTO
     */
    @Override
    protected DTO getDtoIfExistsAndAccessible(ID id) {
        return delegate.getDtoIfExistsAndAccessible(id);
    }

    /**
     * Delegates to {@link DtoStrategyRules#toDto(Object, Object)}.
     *
     * @param source          source object
     * @param mappingStrategy mapping strategy
     * @return DTO
     */
    @Override
    protected DTO toDto(T source, S mappingStrategy) {
        return delegate.toDto(source, mappingStrategy);
    }

    /**
     * Delegates to {@link DtoStrategyRules#toDto(Object)}.
     *
     * @param source source object
     * @return DTO
     */
    @Override
    protected DTO toDto(T source) {
        return delegate.toDto(source);
    }

    /**
     * Delegates to {@link DtoStrategyRules#getDtoMapper()}.
     *
     * @return mapper from source to DTO
     */
    @Override
    protected BiFunction<T, S, DTO> getDtoMapper() {
        return delegate.getDtoMapper();
    }

    /**
     * Delegates to {@link DtoStrategyRules#getDefaultMappingStrategy()}.
     *
     * @return default mapping strategy
     */
    @Override
    protected S getDefaultMappingStrategy() {
        return delegate.getDefaultMappingStrategy();
    }

    /**
     * Delegates to {@link DtoStrategyRules#ifExistsAndAccessible(Object, Function)}.
     *
     * @param id     object ID
     * @param mapper function to call
     * @param <R>    mapper result type
     * @return what the mapper returns
     */
    @Override
    protected <R> R ifExistsAndAccessible(ID id, Function<? super T, R> mapper) {
        return delegate.ifExistsAndAccessible(id, mapper);
    }

}
