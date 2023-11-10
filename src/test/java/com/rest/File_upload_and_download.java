package com.rest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class File_upload_and_download {

    @Test
    public void upload_file(){
        String attributes= "{\n" +
                "  \"type\": \"personal\",\n" +
                "  \"description\": \"This is sample text\"\n" +
                "}";
        given().
                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
                multiPart("file", new File("temp.txt")).
                multiPart("attributes",attributes,"application/json").
                log().all().
        when().
                post("/post").
         then().
                log().all().
                assertThat().statusCode(200);
    }
//    @Test
//    public void download_file(){
//        given().
//                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
//                multiPart("file", new File("temp.txt")).
//                multiPart("attributes",attributes,"application/json").
//                log().all().
//                when().
//                post("/post").
//                then().
//                log().all().
//                assertThat().statusCode(200);
//    }
}
