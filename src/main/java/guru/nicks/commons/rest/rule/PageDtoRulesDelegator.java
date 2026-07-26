package guru.nicks.commons.rest.rule;

import guru.nicks.commons.rest.dto.PageDto;

import org.springframework.data.domain.Page;

import java.util.function.Function;

/**
 * Delegator for {@link PageDtoRules} that delegates all method calls to {@link #getPageDtoRules()}.
 *
 * @param <T>   type of object being managed
 * @param <ID>  object ID type
 * @param <DTO> DTO type
 */
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class PageDtoRulesDelegator<T, ID, DTO> extends PageDtoRules<T, ID, DTO> {

    /**
     * Provides the delegate instance to which all method calls are forwarded.
     *
     * @return delegate instance
     */
    protected abstract PageDtoRules<T, ID, DTO> getPageDtoRules();

    /**
     * Delegates to {@link PageDtoRules#getIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return object
     */
    @Override
    protected T getIfExistsAndAccessible(ID id) {
        return getPageDtoRules().getIfExistsAndAccessible(id);
    }

    /**
     * Delegates to {@link PageDtoRules#ifExistsAndAccessible(Object, Function)}.
     *
     * @param id     object ID
     * @param mapper function to call
     * @param <R>    mapper result type
     * @return what the mapper returns
     */
    @Override
    protected <R> R ifExistsAndAccessible(ID id, Function<? super T, R> mapper) {
        return getPageDtoRules().ifExistsAndAccessible(id, mapper);
    }

    /**
     * Delegates to {@link PageDtoRules#getDtoIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return DTO
     */
    @Override
    protected DTO getDtoIfExistsAndAccessible(ID id) {
        return getPageDtoRules().getDtoIfExistsAndAccessible(id);
    }

    /**
     * Delegates to {@link PageDtoRules#toDto(Object)}.
     *
     * @param source source object
     * @return DTO
     */
    @Override
    protected DTO toDto(T source) {
        return getPageDtoRules().toDto(source);
    }

    /**
     * Delegates to {@link PageDtoRules#getDtoMapper()}.
     *
     * @return mapper from source to DTO
     */
    @Override
    protected Function<T, DTO> getDtoMapper() {
        return getPageDtoRules().getDtoMapper();
    }

    /**
     * Delegates to {@link PageDtoRules#getPageDtoMapper()}.
     *
     * @return mapper from a page of source objects to a page of DTOs
     */
    @Override
    protected Function<Page<T>, PageDto<DTO>> getPageDtoMapper() {
        return getPageDtoRules().getPageDtoMapper();
    }

    /**
     * Delegates to {@link PageDtoRules#toPageDto(Page)}.
     *
     * @param page source page
     * @return DTO page
     */
    @Override
    protected PageDto<DTO> toPageDto(Page<T> page) {
        return getPageDtoRules().toPageDto(page);
    }

}
