package bearServices;

import io.restassured.RestAssured;
import models.Bear;

import static constants.Constants.EndPoints.BEAR_GET;
import static constants.Constants.EndPoints.BEAR_PUT;

public class BearDeletionService {
    public static void deleteAllBears() {
        RestAssured.when()
            .delete(BEAR_GET);
    }

    public static void deleteSpecificBear(Bear bear) {
        RestAssured.when()
            .delete(BEAR_PUT + bear.getBear_id());
    }
}
