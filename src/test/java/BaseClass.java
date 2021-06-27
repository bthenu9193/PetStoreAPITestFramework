import com.qa.main.pojoClass.*;
import com.qa.main.props.testContext;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.lessThan;

/*
 * This is the Base class used by other tests.
 * This consists of all common methods that can be used by various classes.
 * initTest() in Before class is used to get the properties from respective property file or Variable to use it in Test classes i.e status and category
 *
 *  ---Details of methods are explained in the test class GetPetStatus and AddPetAndFindStatus ---
 *
 *
 */

public class BaseClass {
    public static String VAL_PROPS = "val.properties";

    private static String photoUrl="https://www.shutterstock.com/image-photo/single-lion-looking-regal-standing-proudly-555551179";
    private static final Logger log = LoggerFactory.getLogger(BaseClass.class);

    @BeforeClass
    public void initTest() throws Exception {
      testContext.init(getProps());
    }
    protected Pet buildNewPetDetails(String petName, String categoryName, String TagName, Status status,long otherIds) {
        Pet newPet = new PetBuilder()
                .withId(RandomStringUtils.randomNumeric(5))
                .withName(petName)
                .withPhotoUrls(Collections.singletonList(photoUrl))
                .withStatus(status)
                .withTags(Collections.singletonList(new Tag(otherIds, TagName)))
                .inCategory(new Category(otherIds, categoryName)).build();
    return newPet;
    }

    protected void validateResponseCode(Response response) {
        response.then().assertThat().statusCode(200);
    }
    protected void validateResponseTime(Response response) {
        response.then().assertThat().time(lessThan(200L), TimeUnit.MILLISECONDS );
    }

    protected void iteratePetsAndPrintSpecificCategory(Response response, String category) throws Exception {
        List<Pet> petList = response.body().jsonPath().getList("", Pet.class);
        log.info("No. of Pets in the List : " + petList.size());
        ResponseBody body = response.getBody();

        if ((body.asString()).contains(category)) {
            System.out.println("Details of "+ category+":");
            int count=1;
            for (Pet pet : petList) {
                try {
                    if (pet.getCategory().getName().equalsIgnoreCase(category)) {
                        try {
                            log.info("Pet " + count++ + " Details :- \n" + pet.toString());
                        } catch (Exception e) {
                            log.error("Caught Exception in getting pet class values");
                        }
                    }
                }catch (NullPointerException e) {
                    log.error("Caught Null pointer Exception as name is null for referred category");

                }
            }
        }
        else{
            log.warn(category+ " are not Present in the List");
        //    Assert.assertTrue((body.asString()).contains(category),category+ " are not Present in the " +status+" List");
        }
    }

    protected List<Pet> GetPetsOfSpecificCategory(Response response, String category, Status status) throws Exception {
        List<Pet> petList = response.body().jsonPath().getList("", Pet.class);
        ResponseBody body = response.getBody();
        List<Pet> ListOfpetincategory =new ArrayList();
        if ((body.asString()).contains(category)) {
            for (Pet pet : petList) {
                try {
                    if (pet.getCategory().getName().equalsIgnoreCase(category)) {
                       ListOfpetincategory.add(pet);
                    }
                }catch (NullPointerException e) {
                    log.error("Caught Null pointer Exception as name is null for referred category");

                }
            }
        }
        else{
            log.warn(category+ " are not Present in the " +status+" List");
            //    Assert.assertTrue((body.asString()).contains(category),category+ " are not Present in the " +status+" List");
        }
        return ListOfpetincategory;
    }
    private Properties getProps() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(VAL_PROPS);
        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file not found in the path"+VAL_PROPS);
        }
        return properties;
    }

}
