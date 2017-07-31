package com.company.api.bdd.stepdefs.maintypes;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfulGetCarMainTypesForDifferentLocales implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public SuccessfulGetCarMainTypesForDifferentLocales(){

        Given("^User prepares to make request to main-types api for locale \"([^\"]*)\" and manufacturer (\\d+)$", (String locale, Integer manufacturerId) -> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE)
                    .param(constants.LOCALE_KEY, locale)
                    .param(constants.MANUFACTURER, manufacturerId);

        });

        When("^User sends GET request to main-types api$" , () -> {
            response = request.when().get(constants.BASE_URL.concat(constants.MAIN_TYPES_RELATIVE_URL));
        });

        Then("^User receives (\\d+) response$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        Then("^response has a valid schema$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("maintypes-response.json"));
        });

    }
}
