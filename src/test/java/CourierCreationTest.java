import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Courier;
import client.CourierClient;
import model.CourierLogin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class CourierCreationTest {

    private Courier courier;
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
    @DisplayName("Создание курьера")
    public void courierCreationTest() {
        courier = new Courier("shanshan125", "0987123", "Anaaanananstasia");
        ValidatableResponse response = courierClient.createCourier(courier);
        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");
        courierId = courierClient.login(CourierLogin.from(courier)).extract().path("id");
        assertThat(statusCode, equalTo(201));
        assertTrue(isCourierCreated);
    }

    @Test
    @DisplayName("Создание курьера с заполнением обязательных полей")
    public void courierObligatoryFieldsCreationTest(){
        courier = new Courier("tigr777", "00001");
        ValidatableResponse response = courierClient.createCourier(courier);
        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");
        courierId = courierClient.login(CourierLogin.from(courier)).extract().path("id");
        assertThat(statusCode, equalTo(201));
        assertTrue(isCourierCreated);
    }

}
