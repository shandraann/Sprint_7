import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.OrderClient;
import model.OrderData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String[] colour;

    public OrderTest(String[] colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters
    public static Object[][] colorTestData() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{""}}
        };
    }

    @Test
    @DisplayName("Тест создания заказа с самокатами разных цветов")
    public void orderWithDifferentColours(){
        Order order = OrderData.setOrderData().setColour(colour);
        OrderClient orderClient = new OrderClient();
        ValidatableResponse response = orderClient.create(order);
        int statusCode = response.extract().statusCode();
        assertThat(statusCode, equalTo(201));
    }

    @Test
    @DisplayName("Тест с проверкой,что тело ответа содержит track")
    public void checkResponseBodyContainsTrack() {
        Order order = OrderData.setOrderData();
        OrderClient orderClient = new OrderClient();
        ValidatableResponse response = orderClient.create(order);
        int orderTrack = response.extract().path("track");
        assertNotNull(orderTrack);
    }

}

