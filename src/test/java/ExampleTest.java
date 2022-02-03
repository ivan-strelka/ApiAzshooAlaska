import bearServices.*;
import models.Bear;
import models.BearType;
import org.junit.jupiter.api.Test;
import spec.Specifications;

import java.util.List;

import static bearServices.BearReadingService.getStringBear;
import static constants.Constants.BASE_URI;
import static constants.Constants.Words.EMPTY;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ExampleTest extends BaseTest {

    @Test
    public void getInformationRequestReturnsStatusCode200_1() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URI),
                Specifications.responseSpecOK200());
        InformationReadingService.getResponseOfInformationRequest().statusCode();
    }

    @Test
    public void getInformationRequestReturnsStatusCode200_2() {
        int statusCode = InformationReadingService.getResponseOfInformationRequest().statusCode();
        assertThat("The status code is not 200", statusCode, equalTo(HTTP_OK));
    }

    @Test
    public void bearWithValidParametersAfterCreation() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URI),
                Specifications.responseSpecOK200());
        Bear expectedBear = bearCreation.createAnyTypeBear(BearType.POLAR);
        BearCreationService.createBearAndSetItsId(expectedBear);

        Bear actualBear = BearReadingService.getBear(expectedBear);

        assertThat("The bear was wrongly saved", actualBear, equalTo(expectedBear));
    }

    @Test
    public void listOfBearsWithValidParametersAfterCreation() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URI),
                Specifications.responseSpecOK200());

        BearDeletionService.deleteAllBears();

        List<Bear> expectedListOfBears = bearCreation.createListOfBear();

        BearCreationService.createBearsFromList(expectedListOfBears);

        List<Bear> actualListOfBears = BearReadingService.getListOfAllBears();

        assertThat("The actual list differs from the expected list",
                actualListOfBears, equalTo(expectedListOfBears));
    }

    @Test
    public void listOfBearsAfterDeletionOfAllBearsHasSize_0() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URI),
                Specifications.responseSpecOK200());
        List<Bear> expectedListOfBears = bearCreation.createListOfBear();

        BearCreationService.createBearsFromList(expectedListOfBears);

        BearDeletionService.deleteAllBears();

        int sizeOfBears = BearReadingService.getListOfAllBears().size();

        assertThat("The size of bear list is not 0", sizeOfBears, equalTo(0));
    }


    @Test
    public void readingSpecificBearAfterDeleteReturnsMessageEmpty() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URI),
                Specifications.responseSpecOK200());
        Bear bear = bearCreation.createAnyTypeBear(BearType.BROWN);
        BearCreationService.createBearAndSetItsId(bear);

        BearDeletionService.deleteSpecificBear(bear);

        String responseBodyOfReadingBear = getStringBear(bear);

        assertThat("The message from response body is wrong",
                responseBodyOfReadingBear, equalTo(EMPTY));
    }

    // This test shows a defect
    @Test
    public void bearCanbeUpdated() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URI),
                Specifications.responseSpecOK200());
        Bear expectedBear = bearCreation.createAnyTypeBear(BearType.POLAR);
        BearCreationService.createBearAndSetItsId(expectedBear);

        expectedBear.setBear_type(BearType.BLACK);
        expectedBear.setBear_name(BearCreationService.newName());
        expectedBear.setBear_age(BearCreationService.newAge());

        BearUpdateService.updateBear(expectedBear);

        Bear actualBear = BearReadingService.getBear(expectedBear);


        assertThat("the id was wrongly updated",
                actualBear.getBear_id(), equalTo(expectedBear.getBear_id()));

        assertThat("the age was wrongly updated",
                actualBear.getBear_age(), equalTo(expectedBear.getBear_age()));

        assertThat("the name was wrongly updated",
                actualBear.getBear_name(), equalTo(expectedBear.getBear_name()));

        assertThat("the type was wrongly updated",
                actualBear.getBear_type(), equalTo(expectedBear.getBear_type()));

        assertThat("the bear was wrongly updated", actualBear, equalTo(expectedBear));
    }


}
