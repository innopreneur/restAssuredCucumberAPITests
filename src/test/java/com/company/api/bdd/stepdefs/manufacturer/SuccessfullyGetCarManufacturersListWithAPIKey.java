package com.company.api.bdd.stepdefs.manufacturer;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfullyGetCarManufacturersListWithAPIKey implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;

    public SuccessfullyGetCarManufacturersListWithAPIKey(){

        Given("^User has valid api key to access company\\'s car manufacturer apis$",()-> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE);
        });

        When("^User sends GET request to manufacturer api with proper parameters$", () -> {
            response = request.when().get(constants.BASE_URL.concat(constants.MANUFACTURER_RELATIVE_URL));
            System.out.println("Response - " + response.prettyPrint());
        });

        Then("^User receives OK \\((\\d+)\\) success response$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And("^response has valid schema$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("manufacturer-response.json"));
        });

        And("^response includes the list of car manufacturers$", () -> {
            response.then().assertThat().body(constants.WKDA_OBJ, notNullValue());
         
        });
    }
}
