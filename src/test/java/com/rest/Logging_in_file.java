package com.rest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import static  io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class Logging_in_file {

    @Test
    public void logg_in_file() throws FileNotFoundException {
        PrintStream ps= new PrintStream(new File("Restassured.logs"));
        given().
                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
                filter(new RequestLoggingFilter(ps)).
                filter(new ResponseLoggingFilter(LogDetail.STATUS,ps)).
                when().
                get("/get").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeclass() throws FileNotFoundException {
        PrintStream ps= new PrintStream(new File("Restassuredreusefilter.logs"));

        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://postman-echo.com").
                addFilter(new RequestLoggingFilter(ps)).
                addFilter(new ResponseLoggingFilter(ps));
        requestSpecification = requestBuilder.build();      // default req spec from Rest assured

        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        responseSpecification = responseBuilder.build();
    }
    @Test
    public void logg_in_file_reuse_filter() throws FileNotFoundException {
        given(requestSpecification).
                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
                when().
                get("/get").
                then().spec(responseSpecification).
                log().all().
                assertThat().statusCode(200);
    }


}
