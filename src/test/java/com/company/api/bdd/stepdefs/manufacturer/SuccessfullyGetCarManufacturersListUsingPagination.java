package com.company.api.bdd.stepdefs.manufacturer;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfullyGetCarManufacturersListUsingPagination implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;

    public SuccessfullyGetCarManufacturersListUsingPagination(){

        Given("^User has access to get different pages of company\\'s car manufacturer apis$",()-> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE)
                    .param(constants.LOCALE_KEY, constants.LOCALE_VALUE);
        });

        When("^User sends GET request to manufacturer api for page (\\d+)$", (Integer pageNo) -> {
            request.param(constants.PAGE_NO, pageNo);
            response = request.when().get(constants.BASE_URL.concat(constants.MANUFACTURER_RELATIVE_URL));
            System.out.println("Response - " + response.prettyPrint());
        });

        Then("^User receives successful OK \\((\\d+)\\) response from manufacturer api$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And("^response has a valid json schema of manufacturer api$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("manufacturer-response.json"));
        });

    }
}
