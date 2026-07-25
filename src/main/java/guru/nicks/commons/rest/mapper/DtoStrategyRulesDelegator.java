package guru.nicks.commons.rest.mapper;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Delegator for {@link DtoStrategyRules} that delegates all method calls to {@link #getDtoStrategyRules()}}.
 *
 * @param <T>   source type
 * @param <ID>  object ID type
 * @param <S>   mapping strategy type
 * @param <DTO> DTO type
 */
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class DtoStrategyRulesDelegator<T, ID, S, DTO> extends DtoStrategyRules<T, ID, S, DTO> {

    /**
     * Provides the delegate instance to which all method calls are forwarded.
     *
     * @return delegate instance
     */
    protected abstract DtoStrategyRules<T, ID, S, DTO> getDtoStrategyRules();

    /**
     * Delegates to {@link DtoStrategyRules#getIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return object
     */
    @Override
    protected T getIfExistsAndAccessible(ID id) {
        return getDtoStrategyRules().getIfExistsAndAccessible(id);
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
        return getDtoStrategyRules().getDtoIfExistsAndAccessible(id, mappingStrategy);
    }

    /**
     * Delegates to {@link DtoStrategyRules#getDtoIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return DTO
     */
    @Override
    protected DTO getDtoIfExistsAndAccessible(ID id) {
        return getDtoStrategyRules().getDtoIfExistsAndAccessible(id);
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
        return getDtoStrategyRules().toDto(source, mappingStrategy);
    }

    /**
     * Delegates to {@link DtoStrategyRules#toDto(Object)}.
     *
     * @param source source object
     * @return DTO
     */
    @Override
    protected DTO toDto(T source) {
        return getDtoStrategyRules().toDto(source);
    }

    /**
     * Delegates to {@link DtoStrategyRules#getDtoMapper()}.
     *
     * @return mapper from source to DTO
     */
    @Override
    protected BiFunction<T, S, DTO> getDtoMapper() {
        return getDtoStrategyRules().getDtoMapper();
    }

    /**
     * Delegates to {@link DtoStrategyRules#getDefaultMappingStrategy()}.
     *
     * @return default mapping strategy
     */
    @Override
    protected S getDefaultMappingStrategy() {
        return getDtoStrategyRules().getDefaultMappingStrategy();
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
        return getDtoStrategyRules().ifExistsAndAccessible(id, mapper);
    }

}
