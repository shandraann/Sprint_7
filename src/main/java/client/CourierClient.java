package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Courier;
import model.CourierLogin;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {

    private static final String COURIER = "/api/v1/courier";

    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER)
                .then();
    }

    @Step("Логин курьера")
    public ValidatableResponse login(CourierLogin courierLogin){
        return given()
                .spec(getBaseSpec())
                .body(courierLogin)
                .when()
                .post(COURIER + "/login")
                .then();
    }

    @Step("Удаление курьера")
    public boolean delete(int courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER + "/" + courierId)
                .then()
                .extract()
                .path("ok");
    }

}
