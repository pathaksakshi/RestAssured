package com.rest;

import com.rest.pojo.Workspace.Workspace_address;
import com.rest.pojo.Workspace.Workspace_geo;
import com.rest.pojo.Workspace.Workspace_root;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Assignment2_ComplexPOJO {
    ResponseSpecification customResponseSpecification;
    @BeforeClass
    public void beforeclass() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://jsonplaceholder.typicode.com/users").
                addHeader("x-mock-match-request-body", "true");
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification = requestBuilder.build();      // default req spec from Rest assured

        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder().
                expectStatusCode(201).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        //RestAssured.responseSpecification = responseBuilder.build();
        customResponseSpecification= responseBuilder.build();
    }

    @Test
    public void Complex_POJO(){

        Workspace_geo workspaceGeo = new Workspace_geo("-37.3159","81.1496");
        Workspace_address workspaceAddress = new Workspace_address("Kulas Light","Apt. 556","Gwenborough","92998-3874",workspaceGeo);
        Workspace_root workspaceRoot = new Workspace_root(workspaceAddress, "Leanne Graham", "Bret","Sincere@april.biz");

        Workspace_root deserializedworkspaceroot =  given().relaxedHTTPSValidation().
                body(workspaceRoot).
        when().
                post("").
        then().spec(customResponseSpecification).
                extract().response().as(Workspace_root.class);
                assertThat(deserializedworkspaceroot.getId(),not(is(emptyOrNullString())));
    }

}


