package User;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetUserTest extends UserBaseTest {

    @Test
    public void checkGetUser() {

        Response response;

        response = userService.getUserRequest("fandorin");

        response
                .then()
                .spec(responseSpec)
                .log().all()
                .body("firstName", equalTo("Эраст"))
                .body("lastName", comparesEqualTo("Фандорин"));

    }

    @Test
    public void checkGetNonExistentUser() {

        Response response;

        response = userService.getUserRequest("wtf");

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .time(lessThan(5000L))
                .body("type", equalTo("error"))
                .body("message", comparesEqualTo("User not found"));;

    }

}
