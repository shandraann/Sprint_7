package model;

public class CourierLogin {
    public String login;
    public String password;

    public CourierLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierLogin from(Courier courier) {
        return new CourierLogin(courier.login, courier.password);
    }

}