package com.company.api.bdd.stepdefs.builtdates;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UnableToGetCarBuiltDatesListWithInvalidAPIKey implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public UnableToGetCarBuiltDatesListWithInvalidAPIKey(){

        Given("^User doesn't have a valid api key to access company\\'s car built-dates api for manufacturer \"([^\"]*)\" and main-type \"([^\"]*)\"$", (String manufacturerId, String mainType) -> {
            request = given()
                    .param(constants.MANUFACTURER, manufacturerId)
                    .param(constants.MAIN_TYPE, mainType)
                    .param(constants.LOCALE_KEY, constants.LOCALE_VALUE);
        });

        When("^User sends GET request to built-dates api with an invalid \"([^\"]*)\" api key$", (String apiKey) -> {
            request.param(constants.KEY_NAME, apiKey);
            response = request.when().get(constants.BASE_URL.concat(constants.BUILT_DATES_RELATIVE_URL));
        });

        Then("^User receives FORBIDDEN \\((\\d+)\\) response from built-dates api$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And("^response does not include the list of car built-dates with invalid api key$", () -> {
            assertTrue(response.getBody().asString().isEmpty());
        });
    }
}
