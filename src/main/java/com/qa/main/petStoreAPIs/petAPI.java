package com.qa.main.petStoreAPIs;


import com.qa.main.pojoClass.Pet;
import com.qa.main.pojoClass.Status;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

public class petAPI {
    public static String baseUrl = "https://petstore3.swagger.io/api/v3";
    public static String petUrl = baseUrl + "/pet";
   private RequestSpecification reqSpec;


    public petAPI() {
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.setBaseUri(baseUrl);
        reqSpecBuilder.setContentType(ContentType.JSON);
        reqSpecBuilder.log(LogDetail.METHOD);
        reqSpecBuilder.log(LogDetail.URI);
        reqSpec= reqSpecBuilder.build();
    }

    public Response findByStatus(Status status) throws Exception  {

        Response response = given(reqSpec)
                .queryParam("status", status)
                .get(petUrl + "/findByStatus");
        return response;
    }

    public Response addPet(Pet pet) {
        Response response = given(reqSpec)
                .body(pet)
                .post(petUrl);
        return response;
    }

}
