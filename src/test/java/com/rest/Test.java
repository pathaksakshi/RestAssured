package com.rest;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class Test {
    @org.testng.annotations.Test
    public void validate_get_status_code(){
        given().
        when().
        then();
    }
}
