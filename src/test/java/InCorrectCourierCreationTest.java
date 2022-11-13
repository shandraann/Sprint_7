import client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Courier;
import model.CourierLogin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InCorrectCourierCreationTest {

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
    @DisplayName("Создание двух одинаковых курьеров")
    public void duplicatedCourierCreationTest(){
        courier = new Courier("Vasyyan", "random", "Vasillisk");
        courierClient.createCourier(courier);
        courierId = courierClient.login(CourierLogin.from(courier)).extract().path("id");
        courier = new Courier("Vasyyan", "random", "Vasillisk");
        ValidatableResponse response = courierClient.createCourier(courier);
        int statusCode = response.extract().statusCode();
        assertThat(statusCode, equalTo(409));
    }

    @Test
    @DisplayName("Создание курьеров с одинаковыми логинами")
    public void duplicatedLoginTest(){
        courier = new Courier("Strike", "1234rere", "Pavel");
        courierClient.createCourier(courier);
        courierId = courierClient.login(CourierLogin.from(courier)).extract().path("id");
        courier = new Courier("Strike", "09874te", "Sasha");
        ValidatableResponse response = courierClient.createCourier(courier);
        int statusCode = response.extract().statusCode();
        assertThat(statusCode, equalTo(409));
    }

}
