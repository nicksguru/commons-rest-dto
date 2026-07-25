package guru.nicks.commons.rest.mapper;

import guru.nicks.commons.rest.v1.dto.PageDto;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.function.Function;

/**
 * Delegator for {@link PageRules} that delegates all method calls to a provided instance.
 *
 * @param <T>   type of object being managed
 * @param <ID>  object ID type
 * @param <DTO> DTO type
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class PageRulesDelegator<T, ID, DTO> extends PageRules<T, ID, DTO> {

    /**
     * Delegate instance to which all method calls are forwarded.
     */
    @NonNull // Lombok creates runtime nullness check for this own annotation only
    private final PageRules<T, ID, DTO> delegate;

    /**
     * Delegates to {@link DtoRules#getIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return object
     */
    @Override
    protected T getIfExistsAndAccessible(ID id) {
        return delegate.getIfExistsAndAccessible(id);
    }

    /**
     * Delegates to {@link DtoRules#getDtoIfExistsAndAccessible(Object)}.
     *
     * @param id object ID
     * @return DTO
     */
    @Override
    protected DTO getDtoIfExistsAndAccessible(ID id) {
        return delegate.getDtoIfExistsAndAccessible(id);
    }

    /**
     * Delegates to {@link DtoRules#toDto(Object)}.
     *
     * @param source source object
     * @return DTO
     */
    @Override
    protected DTO toDto(T source) {
        return delegate.toDto(source);
    }

    /**
     * Delegates to {@link DtoRules#getDtoMapper()}.
     *
     * @return mapper from source to DTO
     */
    @Override
    protected Function<T, DTO> getDtoMapper() {
        return delegate.getDtoMapper();
    }

    /**
     * Delegates to {@link DtoRules#ifExistsAndAccessible(Object, Function)}.
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
     * Delegates to {@link PageRules#getPageDtoMapper()}.
     *
     * @return mapper from a page of source objects to a page of DTOs
     */
    @Override
    protected Function<Page<T>, PageDto<DTO>> getPageDtoMapper() {
        return delegate.getPageDtoMapper();
    }

    /**
     * Delegates to {@link PageRules#toPageDto(Page)}.
     *
     * @param page source page
     * @return DTO page
     */
    @Override
    protected PageDto<DTO> toPageDto(Page<T> page) {
        return delegate.toPageDto(page);
    }

}
