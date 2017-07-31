package com.company.api.bdd.stepdefs.builtdates;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfulGetCarBuiltDatesListWithValidAPIKey implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public SuccessfulGetCarBuiltDatesListWithValidAPIKey(){

        Given("^User has valid api key to access company\\'s car built-dates api for manufacturer (\\d+) and main-type \"([^\"]*)\"$", (Integer manufacturerId, String mainType) -> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE)
                    .param(constants.MANUFACTURER, manufacturerId)
                    .param(constants.MAIN_TYPE, mainType);
        });

        When("^User sends GET request to built-dates api with valid params$", () -> {
            
            response = request.when().get(constants.BASE_URL.concat(constants.BUILT_DATES_RELATIVE_URL));
        });

        Then("^User receives OK \\((\\d+)\\) response for given params$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        Then("^response has a valid built-dates schema$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("builtdates-response.json"));
        });

        Then("^response includes the list of car built-dates with (\\d+)$", (Integer key) -> {
            response.then().assertThat().body(constants.WKDA_OBJ, hasKey(key));
        });

    }
}
