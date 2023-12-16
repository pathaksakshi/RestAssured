package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POST_Json_array_as_List {
    @BeforeClass
    public void beforeclass() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://74a691ac-bced-4ea8-8f9e-866fc2073f06.mock.pstmn.io").
                addHeader("x-mock-match-request-body", "true");
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification = requestBuilder.build();      // default req spec from Rest assured

        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseBuilder.build();
    }

    @Test
    public void validate_req_payload_json_as_arraylist() {
        HashMap<String, String > obj5001= new HashMap<String, String>();
        obj5001.put("id","5001");
        obj5001.put("type", "None");

        HashMap<String, String > obj5002= new HashMap<String, String>();
        obj5002.put("id","5002");
        obj5002.put("type", "Glazed");

        List<HashMap<String,String >> jsonarraylist = new ArrayList<HashMap<String, String>>();
        jsonarraylist.add(obj5001);
        jsonarraylist.add(obj5002);

        given().relaxedHTTPSValidation().
                body(jsonarraylist).
                when().
                post("/post").
                then().
                log().all().
                assertThat().
                body("msg", equalTo("Successful"));


    }
}
