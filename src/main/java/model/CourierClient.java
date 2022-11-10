package model;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {

    private static final String COURIER = "/api/v1/courier";

    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER)
                .then();
    }

    public ValidatableResponse login(CourierLogin courierLogin){
        return given()
                .spec(getBaseSpec())
                .body(courierLogin)
                .when()
                .post(COURIER + "/login")
                .then();
    }

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
