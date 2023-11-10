package com.rest;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import static  io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Echo_jsonschema_validat {

    @Test
    public void json_schema_validate(){
        given().
                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
                log().all().
        when().
                get("/get").
        then().
                log().all().
                assertThat().statusCode(200).
                body(matchesJsonSchemaInClasspath("EchoGET.json"));

    }

}
