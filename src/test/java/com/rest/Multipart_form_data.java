package com.rest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class Multipart_form_data {

    @Test
    public  void multipart_form_data(){
        given().
                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
                multiPart("foo1","bar1").
                multiPart("foo2", "bar2").
                log().all().
                when().
                post("/post").
                then().
                log().all().
                assertThat().statusCode(200);

    }
}
