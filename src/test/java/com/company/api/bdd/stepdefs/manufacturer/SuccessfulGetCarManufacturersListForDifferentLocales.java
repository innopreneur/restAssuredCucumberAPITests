package com.company.api.bdd.stepdefs.manufacturer;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfulGetCarManufacturersListForDifferentLocales implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public SuccessfulGetCarManufacturersListForDifferentLocales(){

        Given("^User prepares to make request to manufacturer api for locale \"([^\"]*)\"$", (String locale) -> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE)
                    .param(constants.LOCALE_KEY, locale);
        });

        When("^User sends GET request to manufacturer api$" , () -> {
            response = request.when().get(constants.BASE_URL.concat(constants.MANUFACTURER_RELATIVE_URL));
        });

        Then("^User receives (\\d+) response from manufacturer api$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And("^response has a valid schema for manufacturer api$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("manufacturer-response.json"));
        });

    }
}
