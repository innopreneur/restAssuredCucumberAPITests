@main-types
Feature: Get Car Main Types List

  Scenario Outline: User is able to get a list of car main-types using valid api key and manufacturer id
    Given User has valid api key to access company's car main-types api for manufacturer <manufacturerId>
    When User sends GET request to main-types api for <manufacturerId>
    Then User receives OK (200) response
    And response has a valid main-type schema
    And response includes the list of car main-types with <mainType>

    @positive
    Examples:
      | manufacturerId |  mainType   |
      |     107        |    "Azure"    |
      |     130        |      "X1"     |
      |     141        | "Park Avenue" |

  @negative
  Scenario: User is not able to get a list of car main-types using invalid api key
    Given User doesn't have a valid api key to access company's car main-types api for manufacturer "107"
    When User sends GET request to main-types api with an invalid "fakeKey" api key 
    Then User receives FORBIDDEN (403) response from main-types api
    And response does not include the list of car main-types with invalid api key

  @negative
  Scenario: User is not able to get a list of car main-types without an api key
    Given User doesn't have an api key to access company's car main-types api for manufacturer "107"
    When User sends GET request to main-types api without an api key
    Then User receives UNAUTHORIZED (401) response from main-types api
    And response does not include the list of car main-types without an api key

  Scenario Outline: User is able to get a list of car main-types for different locales
    Given User prepares to make request to main-types api for locale <locale> and manufacturer <manufacturerId>
    When User sends GET request to main-types api
    Then User receives <statusCode> response
    And response has a valid schema

    @locale @positive
    Examples:
    | locale  | manufacturerId |statusCode |
    | "en-GB" |     110        |  200      |
    | "ar-QA" |     107        |  200      |
    | "pl-PL" |     141        |  200      |
    | "de-DE" |     110        |  200      |
    | ""      |     141        |  200      |
    
  Scenario Outline: User is able to get a list of car main-types using pagination
    Given User has access to get different pages of company's car main-types apis for manufacturer 110
    When User sends GET request to main-types api for page <pageNo>
    Then User receives successful OK (200) response
    And response has a valid main-type json schema

    @pagination @positive
    Examples:
    | pageNo |
    |   1    |
    |   2    |