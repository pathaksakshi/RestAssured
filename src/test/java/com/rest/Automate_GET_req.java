package com.rest;

import io.restassured.RestAssured.*;
import io.restassured.config.LogConfig;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.Set;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;

public class Automate_GET_req {

    //VALIDATION BY STATUS CODE
        @org.testng.annotations.Test(priority = 1)
        public void validate_get_status_code(){
            given().
                    baseUri("https://api.postman.com").     //URL OF THE POSTMAN
                    header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").     //HEADER HAVING API KEY
            when().
                    get("/workspaces").     //USING GET METHOD AND "WORKSPACES" IS ENDPOINT
            then().
                    log().all().        //LOGS ALL THE RESPONSE IN CONSOLE
                    assertThat().       //THIS METHOD IS USED TO ASSERT THE VALUE IN REPONSE
                    statusCode(200);        //ASSERTING THE STATUS CODE

        }

   //VALIDATION BY RESPONSE BODY
    @org.testng.annotations.Test
    public void validate_get_response_body(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name",hasItems("My Workspace", "MyselfWorkspace"),
                        "workspaces.type",hasItems("personal", "personal"),
                        "workspaces[1].name",equalTo("MyselfWorkspace"),
                       "workspaces.size()",equalTo(2)
                        );
    }

    //EXTRACT RESPONSE BODY
    @org.testng.annotations.Test
    public void extract_response_body(){
        Response res = given().
                baseUri("https://api.postman.com").
                header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").

        when().
                get("/workspaces").

        then().
                log().all().
                assertThat().
                statusCode(200).
                extract().response();
                System.out.println("response = "+res.asString());
    }

    //EXTRACT SINGLE FIELD FROM RESPONSE BODY
    @org.testng.annotations.Test
    public void extract_single_field_response_body(){
        Response res = given().
                baseUri("https://api.postman.com").
                header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").

                when().
                get("/workspaces").

                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().response();
        System.out.println("Workspace name = "+res.path("workspaces[1].name"));
    }

    //ASSERTION ON RESPONSE BODY
    @org.testng.annotations.Test
    public void assertion_response_body(){
        String name = given().
                baseUri("https://api.postman.com").
                header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").

                when().
                get("/workspaces").

                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().response().path("workspaces[1].name");
        System.out.println("Workspace name = "+name);
       // assertThat(name,equalTo("MyselfWorkspace"));    //ASSERTION USING HAMCREST
        Assert.assertEquals(name,"MyselfWorkspace");  // ASSERTION USING TESTNG

        // System.out.println("Workspace name = "+res.path("workspaces[1].name"));
    }

    //REQUEST RESPONSE LOGGING
    @org.testng.annotations.Test
    public void request_response_logging(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").
                log().all().        //PRINTS ALL LOGS OF REQUEST
                log().headers().    //PRINTS ONLY HEADERS OF REQUEST

        when().
                get("/workspaces").

        then().
                log().all().        //PRINTS ALL LOGS OF RESPONSE
                assertThat().
                statusCode(200).
                log().body();
    }

    //LOGGING ONLY IF THERE IS ERROR IN VALIDATION
    @org.testng.annotations.Test
    public void error_logging_only_if_validation_fails(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").     //ORIGINAL KEY
                config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).       //LOGS THE REQ N RES ONLY IF VALIDATION FAILS
                //log().ifValidationFails().      //LOGS REQUEST ONLY IF VALIDATION FAILS

        when().
                get("/workspaces").

        then().
               // log().ifValidationFails().       //LOGS RESPONSE ONLY IF VALIDATION FAILS
                assertThat().
               // statusCode(201).      //CHANGE STATUS CODE TO CHK VALIDATION
                statusCode(200).        //VALIDATING STATUS CODE
                log().body();
    }

    //BLACKLISTING HEADER AS IT HAS SENSITIVE INFORMATION
    @org.testng.annotations.Test
    public void blacklisting_header(){
        Set<String> headers = new HashSet<String>();        //CREATE A COLLECTION OF STRING AND ADD REQUIRED HEADERS TO BLACKLIST
        headers.add("x-api-key");
        headers.add("Accept");
        given().
                baseUri("https://api.postman.com").
                header("x-api-key","PMAK-65250601d8adf6002af78048-a11c13ada70aeb4c673586412d60fb8789").     //API KEY
               // config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key"))).       //HIDING HEADER DETAILS
                config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).      //CALL THE HEADERS COLLECTION THAT ARE BLACKLISTED
                log().all().

        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200);        //VALIDATING STATUS CODE

    }
}
