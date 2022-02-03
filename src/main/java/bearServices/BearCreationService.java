package bearServices;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import models.Bear;
import models.BearType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static constants.Constants.EndPoints.BEAR_GET;

public class BearCreationService {

    public static Faker usFaker = new Faker(new Locale("en-US"));

    public static void createBearAndSetItsId(Bear bear) {
        String response = RestAssured.given()
                .body(bear.getRequestBodyForCreationOrUpdate())
                .when()
                .post(BEAR_GET)
                .then()
                .extract().body().asString();

        bear.setBear_id(Integer.parseInt(response));
    }

    public static void createBearsFromList(List<Bear> listOfBears) {
        listOfBears.forEach(BearCreationService::createBearAndSetItsId);
    }

    public static String newName() {
        return usFaker.name().firstName().toUpperCase(Locale.ROOT);
    }

    public static Integer newAge() {
        return usFaker.number().numberBetween(1, 30);
    }

    public Bear createAnyTypeBear(BearType bearType) {
        String name = usFaker.name().firstName().toUpperCase(Locale.ROOT);
        int age = usFaker.number().numberBetween(1, 30);
        return new Bear(bearType, name, age);
    }

    public List<Bear> createListOfBear() {
        List<Bear> expectedListOfBears = new ArrayList<>();
        expectedListOfBears.add(createAnyTypeBear(BearType.POLAR));
        expectedListOfBears.add(createAnyTypeBear(BearType.BROWN));
        expectedListOfBears.add(createAnyTypeBear(BearType.BLACK));
        return expectedListOfBears;
    }
}
