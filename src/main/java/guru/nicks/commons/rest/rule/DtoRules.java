package guru.nicks.commons.rest.rule;

import java.util.function.Function;

/**
 * Provides uniform methods for mapping between source and DTO. All the methods are {@code protected} to avoid exposing
 * them in controllers inadvertently.
 *
 * @param <T>   type of object being managed
 * @param <ID>  object ID type
 * @param <DTO> DTO type
 */
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class DtoRules<T, ID, DTO> extends AccessRules<T, ID> {

    /**
     * Retrieves an object with the given ID by calling {@link #ifExistsAndAccessible(Object, Function)}. This means
     * non-existing and inaccessible objects should raise the same 'Not Found' exception. Then, maps the object to a DTO
     * using {@link #toDto(Object)}.
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
    @SuppressWarnings({"UnnecessaryLocalVariable", "java:S1488"}) // for debugging
    protected DTO toDto(T source) {
        DTO dto = getDtoMapper().apply(source);
        return dto;
    }

    /**
     * @return rule from source to DTO
     */
    protected abstract Function<T, DTO> getDtoMapper();

}
