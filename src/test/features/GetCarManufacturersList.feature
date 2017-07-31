@manufacturer
Feature: Get Car Manufacturers List

  @positive
  Scenario: User is able to get a list of car manufacturers using valid api key
    Given User has valid api key to access company's car manufacturer apis
    When User sends GET request to manufacturer api with proper parameters
    Then User receives OK (200) success response
    And response has valid schema
    And response includes the list of car manufacturers

  @negative
  Scenario: User is not able to get a list of car manufacturers without an api key
    Given User doesn't have an api key to access company's car manufacturer apis
    When User sends GET request to manufacturer api without api key
    Then User receives UNAUTHORIZED (401) response from manufacturer api
    And response should not contain list of car manufacturers

  @negative
  Scenario: User is not able to get a list of car manufacturers with an invalid api key
    Given User doesn't have a valid api key to access company's car manufacturer apis
    When User sends GET request to manufacturer api with invalid api key
    Then User receives FORBIDDEN (403) error response
    And response should not have list of car manufacturers


  Scenario Outline: User is able to get a list of car manufacturers for different locales
    Given User prepares to make request to manufacturer api for locale <locale>
    When User sends GET request to manufacturer api
    Then User receives <statusCode> response from manufacturer api
    And response has a valid schema for manufacturer api

    @locale @positive
    Examples:
    | locale  |statusCode |
    | "en-GB" |  200      |
    | "ar-QA" |  200      |
    | "pl-PL" |  200      |
    | "de-DE" |  200      |
    | ""      |  200      |

  Scenario Outline: User is able to get a list of car manufacturers using pagination
    Given User has access to get different pages of company's car manufacturer apis
    When User sends GET request to manufacturer api for page <pageNo>
    Then User receives successful OK (200) response from manufacturer api
    And response has a valid json schema of manufacturer api

    @pagination @positive
    Examples:
    | pageNo |
    |   1    |
    |   2    |
