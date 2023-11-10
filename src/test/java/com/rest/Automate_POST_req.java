package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class Automate_POST_req {
    @BeforeClass
    public  void beforeclass(){
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://api.postman.com");
        requestBuilder.addHeader("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789");
        requestBuilder.addHeader("Dummy Header", "Dummy Value");
        requestBuilder.log(LogDetail.ALL);
        requestBuilder.setContentType(ContentType.JSON);
        RestAssured.requestSpecification = requestBuilder.build();      // default req spec from Rest assured

        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseBuilder.build();
    }

    @Test
    public void automate_post(){
        String payload= "{\n" +
                "    \"workspace\": {\n" +
                "        \"id\": \"a6414d05-e7f4-4af2-8c7d-ead8cc527248\",\n" +
                "        \"name\": \"My Rest workspace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"This is your personal workspace\",\n" +
                "        \"visibility\": \"personal\",\n" +
                "        \"createdBy\": \"27398448\",\n" +
                "        \"updatedBy\": \"27398448\",\n" +
                "        \"createdAt\": \"2023-05-13T05:38:01.000Z\",\n" +
                "        \"updatedAt\": \"2023-05-13T05:38:01.000Z\"\n" +
                "    \n" +
                "    }\n" +
                "}";
        given().
                body(payload).
        when().
                post("/workspaces").
        then().
                log().all().
                assertThat().body("workspace.name",equalTo("My Rest workspace"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));

    }
    // SEND PAYLOAD AS JSON FILE
    @Test
    public void file_as_payload(){
        File file= new File("src/main/resources/CreateWorkspacePayload.json");
        given().
                body(file).
                when().
                post("/workspaces").
                then().
                log().all().
                assertThat().body("workspace.name",equalTo("My Rest workspace1"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));

    }

    //NESTED JSON OBJECT AS A MAP
    @Test
    public void nested_json_obj_as_map(){
        HashMap<String, Object> mainobj= new HashMap<String, Object>();
        HashMap<String,String> nestedobj = new HashMap<String, String>();
        nestedobj.put("name","mythirdworkspace");
        nestedobj.put("type", "personal");
        nestedobj.put("description","Rest Assured created this");

        mainobj.put("workspace", nestedobj);
        given().
                body(mainobj).
                when().
                post("/workspaces").
                then().
                log().all().
                assertThat().body("workspace.name",equalTo("mythirdworkspace"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));

    }



}
