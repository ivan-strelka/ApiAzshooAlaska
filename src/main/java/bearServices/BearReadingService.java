package bearServices;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Bear;

import java.util.List;

import static constants.Constants.EndPoints.BEAR_GET;
import static constants.Constants.EndPoints.BEAR_PUT;

public class BearReadingService {
    public static List<Bear> getListOfAllBears() {
        Response response = RestAssured.when()
                .get(BEAR_GET);
        return deserializeListOfBearsFromJson(response);
    }

    public static Bear getBear(Bear bear) {
        Response response = readSpecificBear(bear);
        return deserializeBearFromJson(response);
    }

    public static Response readSpecificBear(Bear bear) {
        return RestAssured.when()
                .get(BEAR_PUT + bear.getBear_id());
    }

    public static Bear deserializeBearFromJson(Response response) {
        return response.getBody().as(Bear.class);
    }

    public static List<Bear> deserializeListOfBearsFromJson(Response response) {
        return response.body().jsonPath().getList(".", Bear.class);
    }

    public static String getStringBear(Bear bear) {
        return BearReadingService
                .readSpecificBear(bear)
                .body().asString();
    }
}
