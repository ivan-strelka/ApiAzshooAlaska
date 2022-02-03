import bearServices.BearCreationService;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

/**
 * POST /bear - create
 * GET /bear - read all bears
 * GET /bear/:id - read specific bear
 * PUT /bear/:id - update specific bear
 * DELETE /bear - delete all bears
 * DELETE /bear/:id - delete specific bear
 */

public class BaseTest {
    public BearCreationService bearCreation = new BearCreationService();

    @BeforeAll
    public static void setUp() {
        RestAssured.registerParser("text/html", Parser.JSON);
    }


}
