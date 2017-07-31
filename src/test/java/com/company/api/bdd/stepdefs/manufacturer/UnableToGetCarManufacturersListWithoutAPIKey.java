package com.company.api.bdd.stepdefs.manufacturer;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNull;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UnableToGetCarManufacturersListWithoutAPIKey implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;

    public UnableToGetCarManufacturersListWithoutAPIKey(){

        Given("^User doesn't have an api key to access company\\'s car manufacturer apis$",()-> {
            request = given()
                    .param(constants.LOCALE_KEY, constants.LOCALE_VALUE);
        });

        When("^User sends GET request to manufacturer api without api key$", () -> {
            response = request.when().get(constants.BASE_URL.concat(constants.MANUFACTURER_RELATIVE_URL));
            System.out.println("Response - " + response.prettyPrint());
        });

        Then("^User receives UNAUTHORIZED \\((\\d+)\\) response from manufacturer api$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And("^response should not have list of car manufacturers$", () -> {
            assertNull(response);
        });
    }
}
