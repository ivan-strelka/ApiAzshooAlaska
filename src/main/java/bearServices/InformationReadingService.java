package bearServices;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static constants.Constants.EndPoints.INFO;


public class InformationReadingService {

    public static Response getResponseOfInformationRequest() {
        return RestAssured.when()
                .get(INFO);
    }
}
