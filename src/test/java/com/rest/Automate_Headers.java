package com.rest;

import io.restassured.RestAssured.*;
import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;
public class Automate_Headers {
    //MULTIPLE HEADERS IN REQ
    @Test
    public void multiple_headers(){
        given().relaxedHTTPSValidation().       //IT IS USED IF WE ARE NOT ADMIN OR USING CORPORATE NETWORK (ANOTHER SOL>>>https://medium.com/expedia-group-tech/how-to-import-public-certificates-into-javas-truststore-from-a-browser-a35e49a806dc)
                baseUri("https://74a691ac-bced-4ea8-8f9e-866fc2073f06.mock.pstmn.io").     //URL OF MOCK SERVER
                header("headerName","value2").     //HEADER EX 2
                header("x-mock-match-request-headers","headerName").
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }

    //MULTIPLE HEADERS IN REQ >>> USING HEADERS OBJECT
    @Test
    public void multiple_headers_using_headerobject(){
        Header header= new Header("headerName","value2");
        Header matchheader= new Header("x-mock-match-request-headers","headerName");
        Headers headers= new Headers(header,matchheader);
        given().relaxedHTTPSValidation().       //IT IS USED IF WE ARE NOT ADMIN OR USING CORPORATE NETWORK (ANOTHER SOL>>>https://medium.com/expedia-group-tech/how-to-import-public-certificates-into-javas-truststore-from-a-browser-a35e49a806dc)
                baseUri("https://74a691ac-bced-4ea8-8f9e-866fc2073f06.mock.pstmn.io").     //URL OF MOCK SERVER
                headers(headers).
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }

    //MULTIPLE HEADERS IN REQ >>> USING HEADERS MAP
    @Test
    public void multiple_headers_using_map(){

        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("headerName","value2");
        headers.put("x-mock-match-request-headers","headerName");

        given().relaxedHTTPSValidation().       //IT IS USED IF WE ARE NOT ADMIN OR USING CORPORATE NETWORK (ANOTHER SOL>>>https://medium.com/expedia-group-tech/how-to-import-public-certificates-into-javas-truststore-from-a-browser-a35e49a806dc)
                baseUri("https://74a691ac-bced-4ea8-8f9e-866fc2073f06.mock.pstmn.io").     //URL OF MOCK SERVER
             headers(headers).
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }

    //MULTI VALUE HEADER IN REQ
    @Test
    public void multi_value_header_in_req(){
        Header header1= new Header("multiValueHeader","value1");
        Header header2= new Header("multiValueHeader","value2");
        Headers headers= new Headers(header1,header2);


        given().relaxedHTTPSValidation().       //IT IS USED IF WE ARE NOT ADMIN OR USING CORPORATE NETWORK (ANOTHER SOL>>>https://medium.com/expedia-group-tech/how-to-import-public-certificates-into-javas-truststore-from-a-browser-a35e49a806dc)
                baseUri("https://74a691ac-bced-4ea8-8f9e-866fc2073f06.mock.pstmn.io").     //URL OF MOCK SERVER
                headers(headers).
                log().headers().

        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }

    //ASSERT RESPONSE HEADER
    @Test
    public void assert_res_header(){
        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("headerName","value2");
        headers.put("x-mock-match-request-headers","headerName");

        given().relaxedHTTPSValidation().       //IT IS USED IF WE ARE NOT ADMIN OR USING CORPORATE NETWORK (ANOTHER SOL>>>https://medium.com/expedia-group-tech/how-to-import-public-certificates-into-javas-truststore-from-a-browser-a35e49a806dc)
                baseUri("https://74a691ac-bced-4ea8-8f9e-866fc2073f06.mock.pstmn.io").     //URL OF MOCK SERVER
                headers(headers).
        when().
                get("/get").
        then().
                assertThat().
                statusCode(200).
                headers("responseHeader","resvalue2", "X-RateLimit-Limit", "120");

    }

    //EXTRACT RESPONSE HEADER
    @Test
    public void extract_res_header(){
        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("headerName","value2");
        headers.put("x-mock-match-request-headers","headerName");

        Headers extractedheaders= given().relaxedHTTPSValidation().//IT IS USED IF WE ARE NOT ADMIN OR USING CORPORATE NETWORK (ANOTHER SOL>>>https://medium.com/expedia-group-tech/how-to-import-public-certificates-into-javas-truststore-from-a-browser-a35e49a806dc)
                baseUri("https://74a691ac-bced-4ea8-8f9e-866fc2073f06.mock.pstmn.io").     //URL OF MOCK SERVER
                headers(headers).
        when().
                get("/get").
        then().
                assertThat().
                statusCode(200).
                extract().headers();
               for(Header header : extractedheaders){
                   System.out.println("header name= " +header.getName() + " , "+ "header value=" +header.getValue());

               }

    }

}
