package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Response_Specification {

    ResponseSpecification responseSpecification;

    @BeforeClass
    public  void beforeclass(){
//        reqspecs = with().baseUri("https://api.postman.com").     //URL OF THE POSTMAN
//                header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").
//                log().all();
        RequestSpecBuilder resspecbuilder = new RequestSpecBuilder();
//                .setBaseUri("").      //alternate method
//                addHeader("X-api-Key", "PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789");
        resspecbuilder.setBaseUri("https://api.postman.com");
        resspecbuilder.addHeader("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789");
        resspecbuilder.addHeader("Dummy Header", "Dummy Value");
        resspecbuilder.log(LogDetail.ALL);
        // resspecs = resspecbuilder.build();
        RestAssured.requestSpecification = resspecbuilder.build();      // default req spec from Rest assured

        responseSpecification= RestAssured.expect().
                statusCode(200).
                contentType(ContentType.JSON).
                log().all();
    }

    @Test
    public void response_body_resspecs(){
         Response response= get("/workspaces")
                .then().spec(responseSpecification)
                 .extract().response();
                assertThat(response.path("workspaces[1].name").toString(), equalTo("MyselfWorkspace"));
    }


}
