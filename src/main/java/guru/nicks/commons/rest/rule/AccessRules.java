package guru.nicks.commons.rest.rule;

import java.util.function.Function;

/**
 * Provides uniform methods for checking access to objects.
 *
 * @param <T>  object type
 * @param <ID> object ID type
 */
@SuppressWarnings("java:S119") // allow non-single-letter type names in generics
public abstract class AccessRules<T, ID> {

    /**
     * Retrieves an object with the given ID by calling {@link #ifExistsAndAccessible(Object, Function)}. This means
     * non-existing and inaccessible objects should raise the same 'Not Found' exception.
     *
     * @param id object ID
     * @return object
     */
    protected T getIfExistsAndAccessible(ID id) {
        return ifExistsAndAccessible(id, Function.identity());
    }

    /**
     * Calls a given function for the object with the specified ID.
     * <p>
     * If the object does not exist OR the current user has no access to it (as per Spring Security context), a <b>'Not
     * Found' exception should be raised in both cases</b>. This is to prevent unauthorized users from knowing if
     * somebody else's objects exists.
     *
     * @param id     object ID
     * @param mapper function to call (in JPA context, make sure a transaction is created to make the function atomic)
     * @param <R>    rule result type
     * @return what the rule returns
     */
    protected abstract <R> R ifExistsAndAccessible(ID id, Function<? super T, R> mapper);

}
