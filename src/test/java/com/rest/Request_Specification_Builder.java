package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Request_Specification_Builder {
//    RequestSpecification resspecs;        //only used when need do not use default ressepc obj of rest assured

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
    }

    @org.testng.annotations.Test
    public void validate_get_status_code_by_reqspecs(){
//        Response res= given(resspecs).get("/workspaces").then().log().all().extract().response();  // is default reqspec is not used
        Response res= get("/workspaces").then().log().all().extract().response();     // if default reqspec is used
        assertThat(res.statusCode(), is(equalTo(200)));
    }

    @Test
    public void validate_get_response_body_by_reqspecs(){
//        Response res= given(resspecs).get("/workspaces").then().log().all().extract().response();     // is default reqspec is not used
        Response res= get("/workspaces").then().log().all().extract().response();      // if default reqspec is used
        assertThat(res.statusCode(), is(equalTo(200)));
        assertThat(res.path("workspaces[1].name").toString(), equalTo("MyselfWorkspace"));
    }

    @Test
    public void queryreqspec(){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
        System.out.println(queryableRequestSpecification.getHeaders());

    }

}
