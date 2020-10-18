package User;

import dto.User;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import services.UserService;

import static org.hamcrest.Matchers.lessThan;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserBaseTest {
    protected UserService userService = new UserService();

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(5000L))
            .build();

    @BeforeAll
    public void beforeAll() {
        User user = User.builder()
                .firstName("Эраст")
                .id(101L)
                .lastName("Фандорин")
                .password("Password")
                .username("fandorin")
                .userStatus(10L)
                .build();

        userService.addUserRequest(user);
    }

    @AfterAll
    public void afterAll() {
        userService.deleteUserRequest("fandorin");
    }
}
