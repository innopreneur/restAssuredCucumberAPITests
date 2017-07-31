package com.company.api.bdd.stepdefs.builtdates;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfulGetCarBuiltDatesUsingPagination implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public SuccessfulGetCarBuiltDatesUsingPagination(){

        Given("^User has access to get different pages of company\\'s car built-dates api for manufacturer \"([^\"]*)\" and main-type \"([^\"]*)\"$", (String manufacturerId, String mainType) -> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE)
                    .param(constants.LOCALE_KEY, constants.LOCALE_VALUE)
                    .param(constants.MANUFACTURER, manufacturerId)
                    .param(constants.MAIN_TYPE, mainType);
        });

        When("^User sends GET request to built-dates api for page (\\d+)$", (Integer pageNo) -> {
            request.param(constants.PAGE_NO, pageNo);
            response = request.when().get(constants.BASE_URL.concat(constants.BUILT_DATES_RELATIVE_URL));
        });

        Then("^User receives successful response with status code (\\d+)$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        Then("^response has a valid built-dates json schema$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("builtdates-response.json"));
        });
    }

}
