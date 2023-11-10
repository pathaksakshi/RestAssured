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
public class RequestQueryParam {

    @Test
    public  void Single_query_param(){
        given().
                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
                param("foo1","bar1").
                log().all().
        when().
                get("/get").
        then().
                log().all().
                assertThat().statusCode(200);

    }

    @Test
    public  void Multiple_query_params(){
        HashMap<String,String> queryparams= new HashMap<String, String>();
        queryparams.put("foo1", "bar1");
        queryparams.put("foo2", "bar2");
        given().
                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
                //param("foo1","bar1").
                        queryParams(queryparams).
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().statusCode(200);

    }

    @Test
    public  void Multi_value_query_params(){
        given().
                baseUri("https://postman-echo.com").relaxedHTTPSValidation().
                //param("foo1","bar1").
                        queryParam("foo1","bar1,bar2,bar3").
                queryParam("foo2","bar4;bar5").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().statusCode(200);

    }

    @Test
    public  void path_param(){
        given().
                baseUri("https://reqres.in/").relaxedHTTPSValidation().
                pathParam("userid","2").
                log().all().
                when().
                get("/api/users/{userid}").
                then().
                log().all().
                assertThat().statusCode(200);

    }
}
