package com.rest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;

public class Request_Specification {
    RequestSpecification reqspecs;

    @BeforeClass
    public  void beforeclass(){
        reqspecs = with().baseUri("https://api.postman.com").     //URL OF THE POSTMAN
                header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").
                log().all();
    }

    @Test
    public void validate_get_status_code_by_reqspecs(){
          Response res= reqspecs.get("/workspaces").then().log().all().extract().response();
          assertThat(res.statusCode(), is(equalTo(200)));
    }

    @Test
    public void validate_get_response_body_by_reqspecs(){
        Response res= reqspecs.get("/workspaces").then().log().all().extract().response();
        assertThat(res.statusCode(), is(equalTo(200)));
        assertThat(res.path("workspaces[1].name").toString(), equalTo("MyselfWorkspace"));
    }
}
