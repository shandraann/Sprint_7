import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.CourierClient;
import model.CourierLogin;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CourierLoginWithoutPasswordTest {

    @Test
    @DisplayName("Тест логинапользователя без поля пароль")
    public void courierLoginWithoutPasswordField() {
        CourierLogin courierLogin = new CourierLogin("Kelly", "");
        CourierClient courierClient = new CourierClient();
        ValidatableResponse response = courierClient.login(courierLogin);
        int statusCode = response.extract().statusCode();
        assertThat(statusCode, equalTo(400));
    }
}
