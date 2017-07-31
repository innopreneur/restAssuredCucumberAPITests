package com.company.api.bdd.stepdefs.maintypes;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfulGetCarMainTypesListWithValidAPIKey implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public SuccessfulGetCarMainTypesListWithValidAPIKey(){

        Given("^User has valid api key to access company\\'s car main-types api for manufacturer (\\d+)$", (Integer manufacturerId) -> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE);
        });

        When("^User sends GET request to main-types api for (\\d+)$", (Integer manufacturerId) -> {
            request.param(constants.MANUFACTURER, manufacturerId);
            response = request.when().get(constants.BASE_URL.concat(constants.MAIN_TYPES_RELATIVE_URL));
        });

        Then("^User receives OK \\((\\d+)\\) response$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        Then("^response has a valid main-type schema$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("maintypes-response.json"));
        });

        Then("^response includes the list of car main-types with \"([^\"]*)\"$", (String key) -> {
            response.then().assertThat().body(constants.WKDA_OBJ, hasKey(key));
        });

    }
}
