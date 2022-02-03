package bearServices;

import io.restassured.RestAssured;
import models.Bear;

import static constants.Constants.EndPoints.BEAR_PUT;

public class BearUpdateService {

    public static void updateBear(Bear expectedBear) {
        RestAssured.given()
                .body(expectedBear.getRequestBodyForCreationOrUpdate())
                .when()
                .put(BEAR_PUT + expectedBear.getBear_id());
    }
}
