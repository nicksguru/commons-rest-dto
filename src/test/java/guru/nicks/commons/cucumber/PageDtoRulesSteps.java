package guru.nicks.commons.cucumber;

import guru.nicks.commons.cucumber.world.PageDtoRulesWorld;
import guru.nicks.commons.rest.dto.PageDto;
import guru.nicks.commons.rest.rule.PageDtoRules;
import guru.nicks.commons.rest.rule.TestPageDtoRules;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for {@link PageDtoRules}.
 */
@RequiredArgsConstructor
public class PageDtoRulesSteps {

    // DI
    private final PageDtoRulesWorld world;

    @Given("source items:")
    public void sourceItems(List<String> items) {
        world.setSourceItems(items);
    }

    @Given("page number {int} and page size {int} with {long} total items")
    public void pageNumberAndPageSizeWithTotalItems(int pageNumber, int pageSize, long totalItems) {
        world.setPageNumber(pageNumber);
        world.setPageSize(pageSize);
        world.setTotalItems(totalItems);
    }

    @When("the page is mapped to a page DTO")
    public void thePageIsMappedToAPageDto() {
        // uppercases each source item
        var rules = new TestPageDtoRules(
                page -> PageDto.of(page, String::toUpperCase),
                String::toUpperCase);

        world.setPageDto(rules.toPageDto(buildPage()));
    }

    @When("a null page is mapped to a page DTO")
    public void aNullPageIsMappedToAPageDto() {
        var rules = new TestPageDtoRules(
                page -> PageDto.of(page, String::toUpperCase),
                String::toUpperCase);

        world.setPageDto(rules.toPageDto(null));
    }

    @Then("the page DTO data should be:")
    public void thePageDtoDataShouldBe(List<String> expected) {
        assertThat(world.getPageDto().data())
                .as("page DTO data")
                .isEqualTo(expected);
    }

    @Then("the page DTO data should be empty")
    public void thePageDtoDataShouldBeEmpty() {
        assertThat(world.getPageDto().data())
                .as("page DTO data")
                .isEmpty();
    }

    @Then("the page DTO offset should be {int}")
    public void thePageDtoOffsetShouldBe(int offset) {
        assertThat(world.getPageDto().metadata().offset())
                .as("page DTO offset")
                .isEqualTo(offset);
    }

    @Then("the page DTO should be the first page")
    public void thePageDtoShouldBeTheFirstPage() {
        assertThat(world.getPageDto().metadata().firstPage())
                .as("page DTO firstPage")
                .isTrue();
    }

    @Then("the page DTO should not be the first page")
    public void thePageDtoShouldNotBeTheFirstPage() {
        assertThat(world.getPageDto().metadata().firstPage())
                .as("page DTO firstPage")
                .isFalse();
    }

    @Then("the page DTO should be the last page")
    public void thePageDtoShouldBeTheLastPage() {
        assertThat(world.getPageDto().metadata().lastPage())
                .as("page DTO lastPage")
                .isTrue();
    }

    @Then("the page DTO should not be the last page")
    public void thePageDtoShouldNotBeTheLastPage() {
        assertThat(world.getPageDto().metadata().lastPage())
                .as("page DTO lastPage")
                .isFalse();
    }

    @Then("the page DTO page items should be {int}")
    public void thePageDtoPageItemsShouldBe(int pageItems) {
        assertThat(world.getPageDto().metadata().pageItems())
                .as("page DTO pageItems")
                .isEqualTo(pageItems);
    }

    @Then("the page DTO total items should be {long}")
    public void thePageDtoTotalItemsShouldBe(long totalItems) {
        assertThat(world.getPageDto().metadata().totalItems())
                .as("page DTO totalItems")
                .isEqualTo(totalItems);
    }

    @Then("the page DTO total pages should be {int}")
    public void thePageDtoTotalPagesShouldBe(int totalPages) {
        assertThat(world.getPageDto().metadata().totalPages())
                .as("page DTO totalPages")
                .isEqualTo(totalPages);
    }

    /**
     * Builds a Spring Data {@link Page} from the world state.
     *
     * @return source page
     */
    private Page<String> buildPage() {
        var content = world.getSourceItems();
        var pageable = PageRequest.of(world.getPageNumber(), world.getPageSize());
        return new PageImpl<>(content, pageable, world.getTotalItems());
    }

}
