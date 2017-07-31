@built-dates @manufacturer @main-types
Feature: Select Type Of Car To Sell

  Scenario Outline: User is able to select the type of car to sell to the company
    Given User has valid access company's car manufacturer, main-type and built-dates apis 
    When User sends GET request to manufacturer api with valid params
    Then User receives OK (200) response from manufacturer api
    And response has a valid manufacturer api schema
    And response includes list of car manufacturers with <manufacturerId>
    When User sends GET request to main-types api with manufacturer as <manufacturerId>
    Then User receives OK (200) response from main-types api
    And response has a valid main-type api schema
    And response includes list of car main-types with <mainType>
    When User sends GET request to built-dates api with manufacturer as <manufacturerId> and main-types as <mainType>
    Then User receives OK (200) response from built-dates api
    And response has a valid built-dates api schema
    And response includes list of car built-dates with <builtDate>
    
    @positive
    Examples:
      | manufacturerId   |  mainType     | builtDate  |
      |     "107"        |    "Azure"    |  2000      |
      |     "130"        |      "X1"     |  2010      |
      |     "141"        | "Park Avenue" |  1995      |
