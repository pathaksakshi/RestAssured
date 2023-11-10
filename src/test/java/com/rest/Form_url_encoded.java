package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import static io.restassured.config.RestAssuredConfig.config;

import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Form_url_encoded {
    @Test
    public  void form_url_encoded_data(){
        given().
                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
                config(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                formParam("key1","value1").
                formParam("key 2", "value 2").
                log().all().
                when().
                post("/post").
                then().
                log().all().
                assertThat().statusCode(200);

    }
}
