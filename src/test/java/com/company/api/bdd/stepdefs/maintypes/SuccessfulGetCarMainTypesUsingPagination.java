package com.company.api.bdd.stepdefs.maintypes;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfulGetCarMainTypesUsingPagination implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public SuccessfulGetCarMainTypesUsingPagination(){

        Given("^User has access to get different pages of company\\'s car main-types apis for manufacturer (\\d+)$", (Integer manufacturerId) -> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE)
                    .param(constants.LOCALE_KEY, constants.LOCALE_VALUE)
                    .param(constants.MANUFACTURER, manufacturerId);
        });

        When("^User sends GET request to main-types api for page (\\d+)$", (Integer pageNo) -> {
            request.param(constants.PAGE_NO, pageNo);
            response = request.when().get(constants.BASE_URL.concat(constants.MAIN_TYPES_RELATIVE_URL));
        });

        Then("^User receives successful OK \\((\\d+)\\) response$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        Then("^response has a valid main-type json schema$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("maintypes-response.json"));
        });
    }

}
