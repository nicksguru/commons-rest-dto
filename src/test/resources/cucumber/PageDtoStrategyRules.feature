@rest-dto
Feature: PageDtoStrategyRules

  Scenario: Map a page using the upper-case mapping strategy
    Given source items:
      | alpha |
      | beta |
    And page number 0 and page size 10 with 2 total items
    And mapping strategy "upper"
    When the page is mapped to a page DTO using the strategy
    Then the page DTO data should be:
      | ALPHA |
      | BETA |
    And the page DTO total items should be 2

  Scenario: Map a page using the lower-case mapping strategy
    Given source items:
      | Alpha |
      | Beta |
    And page number 0 and page size 10 with 2 total items
    And mapping strategy "lower"
    When the page is mapped to a page DTO using the strategy
    Then the page DTO data should be:
      | alpha |
      | beta |
    And the page DTO total items should be 2

  Scenario: Mapping the same page with different strategies produces different results
    Given source items:
      | MixEd |
    And page number 0 and page size 10 with 1 total items
    And mapping strategy "upper"
    When the page is mapped to a page DTO using the strategy
    Then the page DTO data should be:
      | MIXED |
    And mapping strategy "lower"
    When the page is mapped to a page DTO using the strategy
    Then the page DTO data should be:
      | mixed |
