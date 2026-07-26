package guru.nicks.commons.rest.rule;

import guru.nicks.commons.rest.dto.PageDto;

import org.springframework.data.domain.Page;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Minimal concrete implementation of {@link PageDtoStrategyRules} used only in tests. It widens the visibility of the
 * methods under test (by overriding them and delegating to {@code super}) so that step definition classes residing in a
 * different package can exercise the {@code protected} logic.
 */
public class TestPageDtoStrategyRules extends PageDtoStrategyRules<String, Long, String, String> {

    private final BiFunction<Page<String>, String, PageDto<String>> pageDtoMapper;
    private final BiFunction<String, String, String> dtoMapper;
    private final String defaultMappingStrategy;

    /**
     * Constructor.
     *
     * @param pageDtoMapper          rule from a page of source objects and a mapping strategy to a page of DTOs
     * @param dtoMapper              rule from a single source object and a mapping strategy to a DTO
     * @param defaultMappingStrategy default mapping strategy applied by {@link #toDto(Object)}
     */
    public TestPageDtoStrategyRules(BiFunction<Page<String>, String, PageDto<String>> pageDtoMapper,
            BiFunction<String, String, String> dtoMapper, String defaultMappingStrategy) {
        this.pageDtoMapper = pageDtoMapper;
        this.dtoMapper = dtoMapper;
        this.defaultMappingStrategy = defaultMappingStrategy;
    }

    @Override
    protected BiFunction<Page<String>, String, PageDto<String>> getPageDtoMapper() {
        return pageDtoMapper;
    }

    /**
     * Exposes the {@code protected} {@link PageDtoStrategyRules#toPageDto(Page, Object)} to step definition classes.
     *
     * @param page            source page
     * @param mappingStrategy mapping strategy
     * @return DTO page
     */
    @Override
    public PageDto<String> toPageDto(Page<String> page, String mappingStrategy) {
        return super.toPageDto(page, mappingStrategy);
    }

    @Override
    protected BiFunction<String, String, String> getDtoMapper() {
        return dtoMapper;
    }

    @Override
    protected String getDefaultMappingStrategy() {
        return defaultMappingStrategy;
    }

    @Override
    protected <R> R ifExistsAndAccessible(Long id, Function<? super String, R> mapper) {
        // not relevant for page mapping tests
        return null;
    }

}
