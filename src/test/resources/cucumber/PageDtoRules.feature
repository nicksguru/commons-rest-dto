@rest-dto
Feature: PageDtoRules

  Scenario: Map a single non-empty page
    Given source items:
      | alpha |
      | beta |
      | gamma |
    And page number 0 and page size 10 with 3 total items
    When the page is mapped to a page DTO
    Then the page DTO data should be:
      | ALPHA |
      | BETA |
      | GAMMA |
    And the page DTO offset should be 0
    And the page DTO should be the first page
    And the page DTO should be the last page
    And the page DTO page items should be 3
    And the page DTO total items should be 3
    And the page DTO total pages should be 1

  Scenario: Map a middle page of a multi-page result
    Given source items:
      | gamma |
      | delta |
    And page number 1 and page size 2 with 6 total items
    When the page is mapped to a page DTO
    Then the page DTO data should be:
      | GAMMA |
      | DELTA |
    And the page DTO offset should be 1
    And the page DTO should not be the first page
    And the page DTO should not be the last page
    And the page DTO page items should be 2
    And the page DTO total items should be 6
    And the page DTO total pages should be 3

  Scenario: Map the last page of a multi-page result
    Given source items:
      | epsilon |
    And page number 2 and page size 2 with 5 total items
    When the page is mapped to a page DTO
    Then the page DTO data should be:
      | EPSILON |
    And the page DTO offset should be 2
    And the page DTO should not be the first page
    And the page DTO should be the last page
    And the page DTO page items should be 1
    And the page DTO total items should be 5
    And the page DTO total pages should be 3

  Scenario: Map a null page
    When a null page is mapped to a page DTO
    Then the page DTO data should be empty
    And the page DTO offset should be 0
    And the page DTO should be the first page
    And the page DTO should be the last page
    And the page DTO page items should be 0
    And the page DTO total items should be 0
    And the page DTO total pages should be 0
