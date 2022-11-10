import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Courier;
import model.CourierClient;
import model.CourierLogin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CourierLoginTest {
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Тест возможности авторизации курьера с валидными логином и паролем")
    public void courierCanLogin(){
        Courier courier = new Courier("Olleg", "1234");
        ValidatableResponse newCourier = courierClient.createCourier(courier);
        ValidatableResponse response = courierClient.login(CourierLogin.from(courier));
        int statusCode = response.extract().statusCode();
        courierId = courierClient.login(CourierLogin.from(courier)).extract().path("id");
        assertThat(statusCode, equalTo(200));
    }

    @Test
    @DisplayName("Тест авторизации курьера с неправильным паролем")
    public void courierLoginWithWrongPassword() {
        Courier olegCourier = new Courier("Olleg", "1234");
        ValidatableResponse newCourier = courierClient.createCourier(olegCourier);
        Courier courier = new Courier("Olleg", "5678");
        ValidatableResponse response = courierClient.login(CourierLogin.from(courier));
        int statusCode = response.extract().statusCode();
        courierId = courierClient.login(CourierLogin.from(olegCourier)).extract().path("id");
        assertThat(statusCode, equalTo(404));
    }

    @Test
    @DisplayName("Тест запроса успешной авторизации возвращает id курьера")
    public void  successfulRequestReturnsId() {
        Courier courier = new Courier("Olleg", "1234");
        ValidatableResponse newCourier = courierClient.createCourier(courier);
        ValidatableResponse response = courierClient.login(CourierLogin.from(courier));
        int statusCode = response.extract().statusCode();
        courierId = courierClient.login(CourierLogin.from(courier)).extract().path("id");
        assertThat(statusCode, equalTo(200));
    }

}
