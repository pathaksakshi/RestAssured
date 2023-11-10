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
public class Assignment1_ComplexJSON {
    ResponseSpecification customResponseSpecification;
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
        //RestAssured.responseSpecification = responseBuilder.build();
        customResponseSpecification= responseBuilder.build();
    }
    @Test
    public void Complex_json_validate(){
        List<Integer> rbga1= new ArrayList<Integer>();
        rbga1.add(255);
        rbga1.add(255);
        rbga1.add(255);
        rbga1.add(1);

        HashMap<String,Object> code1= new HashMap<String,Object>();
        code1.put("rgba",rbga1);

        HashMap<String,Object> mainmap1= new HashMap<String, Object>();
        mainmap1.put("color","black");
        mainmap1.put("code",code1);
        mainmap1.put("hex","#000");
        mainmap1.put("category","hue");
        mainmap1.put("type","primary");


        List<Integer> rbga2= new ArrayList<Integer>();
        rbga2.add(0);
        rbga2.add(0);
        rbga2.add(0);
        rbga2.add(1);

        HashMap<String,Object> code2= new HashMap<String,Object>();
        code2.put("rgba",rbga2);

        HashMap<String,Object> mainmap2= new HashMap<String, Object>();
        mainmap2.put("color","white");
        mainmap2.put("category","value");
        mainmap2.put("hex","#FFF");
        mainmap2.put("code",code2);

        List<HashMap<String,Object>> maps= new ArrayList<HashMap<String,Object>>();
        maps.add(mainmap1);
        maps.add(mainmap2);

        HashMap<String,Object> main= new HashMap<String,Object>();
        main.put("colors",maps);


        given().relaxedHTTPSValidation().
               body(main).
        when().
                post("/post").
        then().
                log().all().
                assertThat().
                body("msg", equalTo("Successful"));
    }
}
