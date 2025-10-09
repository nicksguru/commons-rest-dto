package guru.nicks.rest.domain;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RestConstants {

    /**
     * URL path (no trailing '/') for internal REST endpoints.
     */
    public static final String INTERNAL_REST_API_V1_PATH = "/internal/v1";

    /**
     * URL path (no trailing '/') for REST endpoints available to users operating on own resources.
     */
    public static final String MY_REST_API_V1_PATH = "/v1/my";

}
