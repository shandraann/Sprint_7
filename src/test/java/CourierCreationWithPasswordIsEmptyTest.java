import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Courier;
import model.CourierClient;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CourierCreationWithPasswordIsEmptyTest {
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Тест создания курьера c пустым полем пароль")
    public void  courierCreationWithoutPassword() {
        Courier courier = new Courier("Henry", "", "Henrik");
        ValidatableResponse response = courierClient.createCourier(courier);
        int statusCode = response.extract().statusCode();
        assertThat(statusCode, equalTo(400));
    }

}
