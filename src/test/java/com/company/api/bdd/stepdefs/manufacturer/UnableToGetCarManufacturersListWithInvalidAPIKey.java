package com.company.api.bdd.stepdefs.manufacturer;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNull;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UnableToGetCarManufacturersListWithInvalidAPIKey implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;

    public UnableToGetCarManufacturersListWithInvalidAPIKey(){

        Given("^User doesn't have a valid api key to access company\\'s car manufacturer apis$",()-> {
            request = given()
                    .param(constants.KEY_NAME, "fakeKey")
                    .param(constants.LOCALE_KEY, constants.LOCALE_VALUE);
        });

        When("^User sends GET request to manufacturer api with invalid api key$", () -> {
            response = request.when().get(constants.BASE_URL.concat(constants.MANUFACTURER_RELATIVE_URL));
            System.out.println("Response - " + response.prettyPrint());
        });

        Then("^User receives FORBIDDEN \\((\\d+)\\) error response$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And("^response should not contain list of car manufacturers$", () -> {
            assertNull(response);
        });
    }
}
