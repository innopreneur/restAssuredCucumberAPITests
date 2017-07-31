package com.company.api.bdd.stepdefs.maintypes;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UnableToGetCarMainTypesListWithInvalidAPIKey implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public UnableToGetCarMainTypesListWithInvalidAPIKey(){

        Given("^User doesn't have a valid api key to access company\\'s car main-types api for manufacturer \"([^\"]*)\"$", (String manufacturerId) -> {
            request = given()
                    .param(constants.MANUFACTURER, manufacturerId)
                    .param(constants.LOCALE_KEY, constants.LOCALE_VALUE);
        });

        When("^User sends GET request to main-types api with an invalid \"([^\"]*)\" api key$", (String apiKey) -> {
            request.param(constants.KEY_NAME, apiKey);
            response = request.when().get(constants.BASE_URL.concat(constants.MAIN_TYPES_RELATIVE_URL));
            System.out.println("Response EMPTY - " + response.prettyPrint());
        });

        Then("^User receives FORBIDDEN \\((\\d+)\\) response from main-types api$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And("^response does not include the list of car main-types with invalid api key$", () -> {
            assertTrue(response.getBody().asString().isEmpty());
        });
    }
}
