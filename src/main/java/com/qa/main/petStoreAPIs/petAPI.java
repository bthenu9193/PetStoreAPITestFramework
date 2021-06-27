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
import static org.hamcrest.Matchers.containsString;

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
    public Response findByAllStatus() throws Exception  {

        Response response = given(reqSpec)
                .queryParam("status", Status.available.toString())
                .queryParam("status", Status.pending.toString())
                .queryParam("status", Status.sold.toString())
                .get(petUrl + "/findByStatus");
        return response;
    }

    public Response addPet(Pet pet) {
        Response response = given(reqSpec)
                .body(pet)
                .post(petUrl);
        return response;
    }

    public Response deletePet(Pet pet) {
        Response response = given(reqSpec)
                .pathParam("petId", pet.getId())
                .delete(petUrl + "/{petId}");
        return response;
    }

    public void verifyPetDeleted(Pet pet) {
                given(reqSpec)
                .pathParam("petId", pet.getId())
                .get(petUrl+ "/{petId}")
                .then()
                .body(containsString("Pet not found"));
    }

}
