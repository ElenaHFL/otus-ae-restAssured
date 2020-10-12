package User;

import dto.User;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class CreateUserTest extends UserBaseTest {

    @Test
    public void checkCreateUserMaximumInfo() {
        Response response;
        User user;
        String actualType;
        String type = "unknown";
        String errorMessageType = "Incorrect userName";
        String expectedType = "unknown";
        Long id = 101L;

        user = User.builder()
                .id(id)
                .userStatus(10L)
                .username("Levis")
                .firstName("FirstName")
                .lastName("LastName")
                .password("Password")
                .phone("8-911-111-11-11")
                .email("call_me@mail.ru")
                .build();

        response = userService.addUserRequest(user);

        response
                .then()
                .spec(responseSpec)
                .log().all()
                .body("type", equalTo(expectedType))
                .body("message", comparesEqualTo(id.toString()));
    }

    @Test
    public void checkCreateBadUser() {
        Response response;
        User user;

        user = User.builder()
                .firstName("FirstName")
                .userStatus(1000000000000000000L)
                .build();

        response = userService.addUserRequest(user);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .time(lessThan(5000L));
    }

    @Test
    public void checkCreateUserWithSameInfo() {
        User user;
        Long id = 101L;

        user = User.builder()
                .firstName("Эраст")
                .id(id)
                .lastName("Фандорин")
                .password("Password")
                .username("fandorin")
                .userStatus(10L)
                .build();

        Response response1 = userService.addUserRequest(user);
        Response response2 = userService.addUserRequest(user);

        response1
                .then()
                .spec(responseSpec)
                .log().all();

        response2
                .then()
                .spec(responseSpec)
                .log().all();
    }
}
