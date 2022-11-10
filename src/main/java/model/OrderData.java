package model;

public class OrderData {

    public static Order setOrderData() {

         String firstName = "Ivan";
         String lastName = "Pertov";
         String address = "Novinskiy bulvar";
         String metroStation = "Smolenskaya";
         String phone = "+7966535353673";
         int rentTime = 10;
         String deliveryDate = "01.01.2023";
         String comment = "Спасибо";
        return new Order(firstName, lastName, address,
                metroStation, phone, rentTime, deliveryDate, comment);
    }

}
