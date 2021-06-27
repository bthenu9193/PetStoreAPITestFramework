import com.qa.main.petStoreAPIs.petAPI;
import com.qa.main.pojoClass.Status;
import com.qa.main.props.testContext;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
 * This class can be used to get the List of specific category[ex.Lions] in specific status[ex. availability,pending,sold]
 * Here category and status can be retrieved from environment Variables, system properties or from properties file at PetStoreAPITestFramework/src/main/resources/val.properties
 * initializeVal() in BeforeClass get and set the values of status and category
 *
 *  ---Details of Other tests---
 *
 * findStatus() calls the API and return response
 * validateStatusCode() validates the response code and asserts if not 200
 * validateTimeTaken() validates response time and asserts if greater than 200ms
 * iterateAndPrintCategoryDetails() Print details of Lions in the list
 *
 */
public class GetPetStatus extends BaseClass {
    private static final Logger log = LoggerFactory.getLogger(GetPetStatus.class);
    petAPI pet=new petAPI();
    Response response;
    Status status;
    String category;
    GetPetStatus(){
    }

    GetPetStatus(Status status,String category){
        this.status= status;
        this.category= category;
    }
    @BeforeClass
    public void initializeVal(){
        this.status= Status.valueOf(testContext.getStatus());
        this.category= testContext.getCategory();
    }

    @Test(priority = 1)
        public void findStatus() throws Exception {
            this.response=pet.findByStatus(status);
    }

    @Test(priority = 2,dependsOnMethods = {"findStatus"})
    public void validateStatusCode(){
        log.info("Validating the Response code...");
        validateResponseCode(this.response);
    }

    @Test(priority = 2,dependsOnMethods = {"findStatus"})
    public void validateTimeTaken() {
        log.info("Validating that time taken to findByStatus request should less than 200ms...");
        validateResponseTime(this.response);
    }

    @Test(priority = 2,dependsOnMethods = {"findStatus"})
    public void iterateAndPrintCategoryDetails() throws Exception {
        log.info("Find and Iterate through "+category+" Category in "+status+" status...");
      iteratePetsAndPrintSpecificCategory(this.response,category);
    }

    @Test(priority = 3,dependsOnMethods = {"iterateAndPrintCategoryDetails"})
    public void iterateAndPrintCategoryDetailsForAllStatus() throws Exception {
        log.info("Find and Iterate through "+category+" Category for All Status");
        this.response=pet.findByAllStatus();
        //As per SwaggerUI multiple values are allowed but as per my observation API takes first parameter status and list those alone.
        validateResponseCode(this.response);
        if(!(response.body().toString().contains("pending")||response.body().toString().contains("sold")))
            log.error("API is not taking subsequent query params,only first status is considered");
        iteratePetsAndPrintSpecificCategory(this.response,category);

    }



}
