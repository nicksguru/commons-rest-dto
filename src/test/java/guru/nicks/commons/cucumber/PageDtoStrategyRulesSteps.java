package guru.nicks.commons.cucumber;

import guru.nicks.commons.cucumber.world.PageDtoRulesWorld;
import guru.nicks.commons.rest.dto.PageDto;
import guru.nicks.commons.rest.rule.PageDtoStrategyRules;
import guru.nicks.commons.rest.rule.TestPageDtoStrategyRules;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * Step definitions for {@link PageDtoStrategyRules}. Setup and verification steps are reused from
 * {@link PageDtoRulesSteps} because Cucumber glue is global.
 */
@RequiredArgsConstructor
public class PageDtoStrategyRulesSteps {

    // DI
    private final PageDtoRulesWorld world;

    /**
     * Transforms an item according to the mapping strategy.
     *
     * @param item     source item
     * @param strategy mapping strategy ({@code upper} or {@code lower})
     * @return transformed item
     */
    private static String transform(String item, String strategy) {
        return "lower".equalsIgnoreCase(strategy)
                ? item.toLowerCase()
                : item.toUpperCase();
    }

    @Given("mapping strategy {string}")
    public void mappingStrategy(String strategy) {
        world.setMappingStrategy(strategy);
    }

    @When("the page is mapped to a page DTO using the strategy")
    public void thePageIsMappedToAPageDtoUsingTheStrategy() {
        // transforms each source item according to the mapping strategy
        var rules = new TestPageDtoStrategyRules(
                (page, strategy) -> PageDto.of(page, item -> transform(item, strategy)),
                PageDtoStrategyRulesSteps::transform,
                "upper");

        var content = world.getSourceItems();
        var pageable = PageRequest.of(world.getPageNumber(), world.getPageSize());
        var page = new PageImpl<>(content, pageable, world.getTotalItems());

        world.setPageDto(rules.toPageDto(page, world.getMappingStrategy()));
    }

}
