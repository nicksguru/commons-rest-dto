package guru.nicks.commons.rest.mapper;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 * Delegator for {@link DtoRules} that delegates all method calls to a provided instance.
 *
 * @param <T>   type of object being managed
 * @param <ID>  object ID type
 * @param <DTO> DTO type
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class DtoRulesDelegator<T, ID, DTO> extends DtoRules<T, ID, DTO> {

    /**
     * Delegate instance to which all method calls are forwarded.
     */
    @NonNull // Lombok creates runtime nullness check for this own annotation only
    private final DtoRules<T, ID, DTO> delegate;

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

}
