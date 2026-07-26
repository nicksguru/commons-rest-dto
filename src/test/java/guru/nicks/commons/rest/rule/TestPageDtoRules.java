package guru.nicks.commons.rest.rule;

import guru.nicks.commons.rest.dto.PageDto;

import org.springframework.data.domain.Page;

import java.util.function.Function;

/**
 * Minimal concrete implementation of {@link PageDtoRules} used only in tests. It widens the visibility of the methods
 * under test (by overriding them and delegating to {@code super}) so that step definition classes residing in a
 * different package can exercise the {@code protected} logic.
 */
public class TestPageDtoRules extends PageDtoRules<String, Long, String> {

    private final Function<Page<String>, PageDto<String>> pageDtoMapper;
    private final Function<String, String> dtoMapper;

    /**
     * @param pageDtoMapper rule from a page of source objects to a page of DTOs
     * @param dtoMapper     rule from a single source object to a DTO
     */
    public TestPageDtoRules(Function<Page<String>, PageDto<String>> pageDtoMapper, Function<String, String> dtoMapper) {
        this.pageDtoMapper = pageDtoMapper;
        this.dtoMapper = dtoMapper;
    }

    @Override
    protected Function<Page<String>, PageDto<String>> getPageDtoMapper() {
        return pageDtoMapper;
    }

    /**
     * Exposes the {@code protected} {@link PageDtoRules#toPageDto(Page)} to step definition classes.
     *
     * @param page source page
     * @return DTO page
     */
    @Override
    public PageDto<String> toPageDto(Page<String> page) {
        return super.toPageDto(page);
    }

    @Override
    protected Function<String, String> getDtoMapper() {
        return dtoMapper;
    }

    @Override
    protected <R> R ifExistsAndAccessible(Long id, Function<? super String, R> mapper) {
        // not relevant for page mapping tests
        return null;
    }

}
