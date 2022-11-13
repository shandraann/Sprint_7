import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Order;
import client.OrderClient;
import model.OrderData;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class OrderResponseBodyCheckTest {

        @Test
        @DisplayName("Тест с проверкой,что тело ответа содержит track")
        public void checkResponseBodyContainsTrack() {
            Order order = OrderData.setOrderData();
            OrderClient orderClient = new OrderClient();
            ValidatableResponse response = orderClient.create(order);
            int orderTrack = response.extract().path("track");
            assertNotNull(orderTrack);
        }

        @Test
        @DisplayName("Тест с проверкой, что в тело ответа возвращается список заказов")
        public void checkResponseBodyContainsListOfOrders() {
            OrderClient orderClient = new OrderClient();
            ValidatableResponse response = orderClient.getAll();
            List<Object> orders = response.extract().jsonPath().getList("orders");
            assertNotNull(orders);
        }

    }



