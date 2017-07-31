package com.company.api.bdd.stepdefs.builtdates;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfulGetCarBuiltDatesForDifferentLocales implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public SuccessfulGetCarBuiltDatesForDifferentLocales(){

        Given("^User prepares to make request to built-dates api for locale \"([^\"]*)\", manufacturer (\\d+) and main-type \"([^\"]*)\"$", (String locale, Integer manufacturerId, String mainType) -> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE)
                    .param(constants.LOCALE_KEY, locale)
                    .param(constants.MANUFACTURER, manufacturerId)
                    .param(constants.MAIN_TYPE, mainType);

        });

        When("^User sends GET request to built-dates api$" , () -> {
            response = request.when().get(constants.BASE_URL.concat(constants.BUILT_DATES_RELATIVE_URL));
        });

        Then("^User receives (\\d+) response code$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        Then("^response has a valid schema for built-dates json$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("builtdates-response.json"));
        });

    }
}
