package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {

    private static final String ORDER = "/api/v1/orders";

    @Step("POST запрос /api/v1/orders")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER)
                .then();
    }

    @Step("Get запрос /api/v1/orders" )
    public ValidatableResponse getAll() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER)
                .then();
    }

}
