package guru.nicks.commons.cucumber.world;

import guru.nicks.commons.rest.dto.PageDto;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Domain-specific state shared between scenario steps. Thanks to {@link ScenarioScope @ScenarioScope}, each scenario
 * gets a fresh copy.
 */
@Component
@ScenarioScope
@Data
public class PageDtoRulesWorld {

    private List<String> sourceItems;
    private int pageNumber;
    private int pageSize;
    private long totalItems;
    private String mappingStrategy;

    private PageDto<String> pageDto;

}
