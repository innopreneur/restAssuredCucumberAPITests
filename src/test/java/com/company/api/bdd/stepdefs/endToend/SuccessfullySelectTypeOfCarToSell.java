package com.company.api.bdd.stepdefs.endToend;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;

import com.company.api.bdd.constants.Constants;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfullySelectTypeOfCarToSell implements En{

    private Constants constants= new Constants();
    private Response response;
    private RequestSpecification request;
    
    public SuccessfullySelectTypeOfCarToSell(){

        Given("^User has valid access company's car manufacturer, main-type and built-dates apis$", () -> {
            request = given()
                    .param(constants.KEY_NAME, constants.KEY_VALUE);
        });

        When("^User sends GET request to manufacturer api with valid params$" , () -> {
            response = request.when().get(constants.BASE_URL.concat(constants.MANUFACTURER_RELATIVE_URL));
        });

        Then("^User receives OK \\((\\d+)\\) response from manufacturer api$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And ("^response has a valid manufacturer api schema$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("manufacturer-response.json"));
        });
        
        And ("^response includes list of car manufacturers with \"([^\"]*)\"$", (String manufacturerId) -> {
            response.then().assertThat().body(constants.WKDA_OBJ, hasKey(manufacturerId));
        });

        When("^User sends GET request to main-types api with manufacturer as \"([^\"]*)\"$" , (String manufacturerId) -> {
            request.param(constants.MANUFACTURER, manufacturerId);
            response = request.when().get(constants.BASE_URL.concat(constants.MAIN_TYPES_RELATIVE_URL));
        });

        Then("^User receives OK \\((\\d+)\\) response from main-types api$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And ("^response has a valid main-type api schema$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("maintypes-response.json"));
        });
        
        And ("^response includes list of car main-types with \"([^\"]*)\"$", (String mainType) -> {
            response.then().assertThat().body(constants.WKDA_OBJ, hasKey(mainType));
        });

        When("^User sends GET request to built-dates api with manufacturer as \"([^\"]*)\" and main-types as \"([^\"]*)\"$" , (String manufacturerId, String mainType) -> {
            request.param(constants.MANUFACTURER, manufacturerId)
                    .param(constants.MAIN_TYPE, mainType);
            response = request.when().get(constants.BASE_URL.concat(constants.BUILT_DATES_RELATIVE_URL));
        });

        Then("^User receives OK \\((\\d+)\\) response from built-dates api$", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And ("^response has a valid built-dates api schema$", () -> {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("maintypes-response.json"));
        });
        
        And ("^response includes list of car built-dates with (\\d+)$", (String builtDate) -> {
            response.then().assertThat().body(constants.WKDA_OBJ, hasKey(builtDate));
        });

    }
}
