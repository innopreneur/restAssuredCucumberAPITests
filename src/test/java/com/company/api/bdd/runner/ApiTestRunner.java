package com.company.api.bdd.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, 
glue = {"com.company.api.bdd.stepdefs"},
features = {"src/test/features/"},
plugin = { "pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json" },
tags = {"@manufacturer,@built-dates, @main-types"}
)
public class ApiTestRunner {}
