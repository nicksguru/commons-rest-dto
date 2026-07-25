package guru.nicks.commons.rest.mapper;

import guru.nicks.commons.rest.v1.dto.PageDto;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Delegator class for {@link PageStrategyRules} that delegates all method calls to a provided delegate instance.
 *
 * @param <T>   source type
 * @param <ID>  object ID type
 * @param <S>   mapping strategy type
 * @param <DTO> DTO type
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class PageStrategyRulesDelegator<T, ID, S, DTO>
        extends PageStrategyRules<T, ID, S, DTO> {

    /**
     * Delegate instance to which all method calls are forwarded.
     */
    protected final PageStrategyRules<T, ID, S, DTO> delegate;

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

    /**
     * Delegates to {@link PageStrategyRules#getPageDtoMapper()}.
     *
     * @return mapper from a page of source objects to a page of DTOs
     */
    @Override
    protected BiFunction<Page<T>, S, PageDto<DTO>> getPageDtoMapper() {
        return delegate.getPageDtoMapper();
    }

    /**
     * Delegates to {@link PageStrategyRules#toPageDto(Page, Object)}.
     *
     * @param page            source page
     * @param mappingStrategy mapping strategy
     * @return DTO page
     */
    @Override
    protected PageDto<DTO> toPageDto(Page<T> page, S mappingStrategy) {
        return delegate.toPageDto(page, mappingStrategy);
    }

}
