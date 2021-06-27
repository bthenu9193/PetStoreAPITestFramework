import com.qa.main.petStoreAPIs.petAPI;
import com.qa.main.pojoClass.Pet;
import com.qa.main.pojoClass.Status;
import com.qa.main.props.testContext;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
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
 * [Only for Sold Also added test to delete all the pets of specific category and verify whether the pet is deleted.
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

    //Build new pet and add it via API (status available)

    @Test(priority = 1)
    public void buildAndAddPetToAvailable() {
        Pet newPet = buildNewPetDetails("Simbha", category, "Regal",Status.available,012);
        response=pet.addPet(newPet);
        validateResponseCode(response);
    }

    //calls the findByStatus API with status available, validates the response code and
    // Print details of specific Animal selected via Category


    @Test(priority = 2)
    public void findStatusInAvailable() throws Exception {
        response=pet.findByStatus(Status.available);
        validateResponseCode(response);
        iteratePetsAndPrintSpecificCategory(response,category);
    }

    //Build new pet and add it via API (status pending)
    @Test(priority = 3)
    public void buildAndAddPetToPending() {
        Pet newPet = buildNewPetDetails("Mufhaza", category, "Majesty",Status.pending,12);
        response=pet.addPet(newPet);
        validateResponseCode(response);
    }

    //calls the findByStatus API with status pending, validates the response code and
    // Print details of specific Animal selected via Category

    @Test(priority = 4)
    public void findStatusInPending() throws Exception {
        response=pet.findByStatus(Status.pending);
        validateResponseCode(response);
        iteratePetsAndPrintSpecificCategory(response,category);
    }

    //Build new pet and add it via API (status sold)
    @Test(priority = 5)
    public void buildAndAddPetToSold() {
        Pet newPet = buildNewPetDetails("Nala", category, "Lioness",Status.sold,017);
        response=pet.addPet(newPet);
        validateResponseCode(response);
    }
    //calls the findByStatus API with status sold, validates the response code and
    //Print details of specific Animal selected via Category
    //Delete the pets in specific category and verify whether the item is deleted

    @Test(priority = 6)
    public void findStatusInSold() throws Exception {
        response = pet.findByStatus(Status.sold);
        validateResponseCode(response);
        iteratePetsAndPrintSpecificCategory(response, category);
        List<Pet> petList = GetPetsOfSpecificCategory(response, category, Status.sold);
        if(!petList.isEmpty())
        for (Pet petItem : petList) {
            response = pet.deletePet(petItem);
            validateResponseCode(response);
            // Set wait as sometimes it takes little more time to delete from the database
            pet.verifyPetDeleted(petItem);
        }
        //This will verify whether the code for no pets in the specific category is handled or not
        response = pet.findByStatus(Status.sold);
        validateResponseCode(response);
        iteratePetsAndPrintSpecificCategory(response, category);
    }

}
