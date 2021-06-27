import com.qa.main.petStoreAPIs.petAPI;
import com.qa.main.pojoClass.Pet;
import com.qa.main.pojoClass.Status;
import com.qa.main.props.testContext;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/*
 * This class can be used to add new pet and find the status of specific category[ex.Lions] in specific status[ex. available](other values pending,sold)
 * Here category can be retrieved from environment Variables, system properties or from properties file at PetStoreAPITestFramework/src/main/resources/val.properties
 * initialize() in BeforeClass get and set the value of  category
 *
 *  ---Details of Other tests---
 * buildAndAddPetToAvailable() build and add a new pet via API with status available
 * findStatusInAvailable() calls the findByStatus API with status available, validates the response code and Print details of Lions
 * buildAndAddPetToPending() build and add a new pet via API with status pending
 * findStatusInPending() calls the findByStatus API with status pending , validates the response code and Print details of Lions
 * buildAndAddPetToSold() build and add a new pet via API with status sold
 * findStatusInSold() calls the findByStatus API with status sold, validates the response code and Print details of Lions
 *
 *
 */

public class AddPetAndFindStatus extends BaseClass {
    private static final Logger log = LoggerFactory.getLogger(AddPetAndFindStatus.class);
    petAPI pet=new petAPI();
    Response response;
    String category;

    @BeforeClass
    public void initialize(){
        this.category= testContext.getCategory();
    }
    @Test(priority = 1)
    public void buildAndAddPetToAvailable() {
        Pet newPet = buildNewPetDetails("Simbha", category, "Regal",Status.available,012);
        this.response=pet.addPet(newPet);
        validateResponseCode(this.response);
    }
    @Test(priority = 2)
    public void findStatusInAvailable() throws Exception {
        GetPetStatus getstatusbyAvailable= new GetPetStatus(Status.available,category);
        getstatusbyAvailable.findStatus();
        getstatusbyAvailable.validateStatusCode();
        getstatusbyAvailable.iterateAndPrintCategoryDetails();
    }
    @Test(priority = 3)
    public void buildAndAddPetToPending() {
        Pet newPet = buildNewPetDetails("Mufhaza", category, "Majesty",Status.pending,12);
        this.response=pet.addPet(newPet);
        validateResponseCode(this.response);
    }
    @Test(priority = 4)
    public void findStatusInPending() throws Exception {
        GetPetStatus getstatusbyPending= new GetPetStatus(Status.pending,category);
        getstatusbyPending.findStatus();
        getstatusbyPending.validateStatusCode();
        getstatusbyPending.iterateAndPrintCategoryDetails();
    }
    @Test(priority = 5)
    public void buildAndAddPetToSold() {
        Pet newPet = buildNewPetDetails("Nala", category, "Lioness",Status.sold,017);
        this.response=pet.addPet(newPet);
        validateResponseCode(this.response);
    }
    @Test(priority = 6)
    public void findStatusInSold() throws Exception {
        GetPetStatus getstatusbySold= new GetPetStatus(Status.sold,category);
        getstatusbySold.findStatus();
        getstatusbySold.validateStatusCode();
        getstatusbySold.iterateAndPrintCategoryDetails();

    }

}
