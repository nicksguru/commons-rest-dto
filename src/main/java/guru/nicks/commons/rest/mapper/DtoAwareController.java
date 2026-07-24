package guru.nicks.commons.rest.mapper;

import java.util.function.Function;

/**
 * Provides uniform methods for mapping between source and DTO.
 *
 * @param <T>   type of object being managed
 * @param <ID>  object ID type
 * @param <DTO> DTO type
 */
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class DtoAwareController<T, ID, DTO> {

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
     * {@link #toDto(Object)}.
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
     * @param source source object
     * @return DTO
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    protected DTO toDto(T source) {
        DTO dto = getDtoMapper().apply(source);
        return dto;
    }

    /**
     * @return mapper from source to DTO
     */
    protected abstract Function<T, DTO> getDtoMapper();

    /**
     * Runs a given mapper function for the object with the specified ID.
     * <p>
     * If the object does not exist OR the current user has access to it, the same <b>'Not Found' exception should be
     * thrown</b> - to prevent users from knowing if somebody else's objects exists.
     *
     * @param id  object ID
     * @param <R> mapper result type
     * @return what the mapper returns
     */
    protected abstract <R> R ifExistsAndAccessible(ID id, Function<? super T, R> mapper);

}
