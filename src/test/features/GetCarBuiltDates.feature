@built-dates
Feature: Get Car Built Dates List

  Scenario Outline: User is able to get a list of car built-dates using valid api key, manufacturer id and main-type
    Given User has valid api key to access company's car built-dates api for manufacturer <manufacturerId> and main-type <mainType> 
    When User sends GET request to built-dates api with valid params
    Then User receives OK (200) response for given params
    And response has a valid built-dates schema
    And response includes the list of car built-dates with <builtDates>
    
    @positive
    Examples:
      | manufacturerId |  mainType     | builtDates |
      |     107        |    "Azure"    |  2000      |
      |     130        |      "X1"     |  2010      |
      |     141        | "Park Avenue" |  1995      |

  @negative
  Scenario: User is not able to get a list of car built-dates using invalid api key
    Given User doesn't have a valid api key to access company's car built-dates api for manufacturer "700" and main-type "Firebird"
    When User sends GET request to built-dates api with an invalid "fakeKey" api key 
    Then User receives FORBIDDEN (403) response from built-dates api
    And response does not include the list of car built-dates with invalid api key

  @negative
  Scenario: User is not able to get a list of car built-dates without an api key
    Given User doesn't have an api key to access company's car built-dates api for manufacturer "700" and main-type "Firebird"
    When User sends GET request to built-dates api without an api key
    Then User receives UNAUTHORIZED (401) response from built-dates api
    And response does not include the list of car built-dates without an api key

  Scenario Outline: User is able to get a list of car built-dates for different locales
    Given User prepares to make request to built-dates api for locale <locale>, manufacturer <manufacturerId> and main-type <mainType>
    When User sends GET request to built-dates api
    Then User receives <statusCode> response code
    And response has a valid schema for built-dates json

    @locale @positive
    Examples:
    | locale  | manufacturerId |   mainType    |statusCode |
    | "ar-QA" |     107        |    "Azure"    |  200      |
    | "pl-PL" |     141        | "Park Avenue" |  200      |
    | "de-DE" |     130        |     "X1"      |  200      |
    | ""      |     130        |     "Z1"      |  200      |
    
  Scenario Outline: User is able to get a list of car built-dates using pagination
    Given User has access to get different pages of company's car built-dates api for manufacturer "700" and main-type "Firebird"
    When User sends GET request to built-dates api for page <pageNo>
    Then User receives successful response with status code 200
    And response has a valid built-dates json schema

    @pagination @positive
    Examples:
    | pageNo |
    |   1    |
    |   2    |