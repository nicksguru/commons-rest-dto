package guru.nicks.commons.rest.mapper;

import guru.nicks.commons.rest.v1.dto.PageDto;

import org.springframework.data.domain.Page;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Delegator for {@link PageDtoStrategyRules} that delegates all method calls to {@link #getPageDtoStrategyRules()}.
 *
 * @param <T>   source type
 * @param <ID>  object ID type
 * @param <S>   mapping strategy type
 * @param <DTO> DTO type
 */
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class PageDtoStrategyRulesDelegator<T, ID, S, DTO> extends PageDtoStrategyRules<T, ID, S, DTO> {

    /**
     * Provides the delegate instance to which all method calls are forwarded.
     *
     * @return delegate instance
     */
    protected abstract PageDtoStrategyRules<T, ID, S, DTO> getPageDtoStrategyRules();

    /**
     * Delegates to {@link PageDtoStrategyRules#getIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return object
     */
    @Override
    protected T getIfExistsAndAccessible(ID id) {
        return getPageDtoStrategyRules().getIfExistsAndAccessible(id);
    }

    /**
     * Delegates to {@link PageDtoStrategyRules#getDtoIfExistsAndAccessible(Object, Object)}.
     *
     * @param id              object ID
     * @param mappingStrategy mapping strategy
     * @return DTO
     */
    @Override
    protected DTO getDtoIfExistsAndAccessible(ID id, S mappingStrategy) {
        return getPageDtoStrategyRules().getDtoIfExistsAndAccessible(id, mappingStrategy);
    }

    /**
     * Delegates to {@link PageDtoStrategyRules#getDtoIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return DTO
     */
    @Override
    protected DTO getDtoIfExistsAndAccessible(ID id) {
        return getPageDtoStrategyRules().getDtoIfExistsAndAccessible(id);
    }

    /**
     * Delegates to {@link PageDtoStrategyRules#toDto(Object, Object)}.
     *
     * @param source          source object
     * @param mappingStrategy mapping strategy
     * @return DTO
     */
    @Override
    protected DTO toDto(T source, S mappingStrategy) {
        return getPageDtoStrategyRules().toDto(source, mappingStrategy);
    }

    /**
     * Delegates to {@link PageDtoStrategyRules#toDto(Object)}.
     *
     * @param source source object
     * @return DTO
     */
    @Override
    protected DTO toDto(T source) {
        return getPageDtoStrategyRules().toDto(source);
    }

    /**
     * Delegates to {@link PageDtoStrategyRules#getDtoMapper()}.
     *
     * @return mapper from source to DTO
     */
    @Override
    protected BiFunction<T, S, DTO> getDtoMapper() {
        return getPageDtoStrategyRules().getDtoMapper();
    }

    /**
     * Delegates to {@link PageDtoStrategyRules#getDefaultMappingStrategy()}.
     *
     * @return default mapping strategy
     */
    @Override
    protected S getDefaultMappingStrategy() {
        return getPageDtoStrategyRules().getDefaultMappingStrategy();
    }

    /**
     * Delegates to {@link PageDtoStrategyRules#ifExistsAndAccessible(Object, Function)}.
     *
     * @param id     object ID
     * @param mapper function to call
     * @param <R>    mapper result type
     * @return what the mapper returns
     */
    @Override
    protected <R> R ifExistsAndAccessible(ID id, Function<? super T, R> mapper) {
        return getPageDtoStrategyRules().ifExistsAndAccessible(id, mapper);
    }

    /**
     * Delegates to {@link PageDtoStrategyRules#getPageDtoMapper()}.
     *
     * @return mapper from a page of source objects to a page of DTOs
     */
    @Override
    protected BiFunction<Page<T>, S, PageDto<DTO>> getPageDtoMapper() {
        return getPageDtoStrategyRules().getPageDtoMapper();
    }

    /**
     * Delegates to {@link PageDtoStrategyRules#toPageDto(Page, Object)}.
     *
     * @param page            source page
     * @param mappingStrategy mapping strategy
     * @return DTO page
     */
    @Override
    protected PageDto<DTO> toPageDto(Page<T> page, S mappingStrategy) {
        return getPageDtoStrategyRules().toPageDto(page, mappingStrategy);
    }

}
