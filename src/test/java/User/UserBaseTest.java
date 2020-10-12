package User;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import services.UserService;

import static org.hamcrest.Matchers.lessThan;

public class UserBaseTest {
    protected UserService userService = new UserService();

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(5000L))
            .build();
}
