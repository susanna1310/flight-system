package hello.world.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Website {
    List<Flight> flights;

    public Website(Cities cities) {
        this.flights = createFlightList(cities);
    }


    private List<Flight> createFlightList(Cities cities) {
        Flight f1 = new Flight("LH 2423", LocalDateTime.now(),LocalDateTime.parse("2023-12-04T12:45:30"),"Lufthansa", 155, 329,cities.getCity("Paris"), cities.getCity("Munich"), "Munich", "Paris");
        Flight f3 = new Flight("KL 5625", LocalDateTime.parse("2023-12-09T12:45:30"),LocalDateTime.parse("2022-12-09T13:45:30"),"KLM", 169, 319,cities.getCity("Amsterdam"), cities.getCity("Munich"), "Munich", "Amsterdam");
        Flight f4 = new Flight("LH 2423", LocalDateTime.parse("2023-10-04T20:15:30"),LocalDateTime.parse("2023-10-04T20:45:30"),"Lufthansa", 182, 423, cities.getCity("Frankfurt"),cities.getCity("Munich"), "Munich", "Frankfurt");
        Flight f5 = new Flight("QR 0112", LocalDateTime.parse("2023-02-05T07:45:30"),LocalDateTime.parse("2023-02-05T12:25:30"),"Qatar\nAirways", 299, 1549, cities.getCity("Doha"), cities.getCity("Munich"), "Munich", "Doha");
        Flight f6 = new Flight("OK 2137", LocalDateTime.parse("2023-03-15T08:30:30"),LocalDateTime.parse("2023-03-15T11:40:30"),"CSA\nCzech Airlines", 99, 249, cities.getCity("Brno"), cities.getCity("Munich"), "Munich", "Brno");
        Flight f7 = new Flight("AA 1091", LocalDateTime.parse("2023-02-05T10:45:30"),LocalDateTime.parse("2023-02-05T13:45:30"),"Lufthansa", 799, 1899, cities.getCity("New York"), cities.getCity("Frankfurt"), "Frankfurt", "New York");
        Flight f8 = new Flight("D8 9487", LocalDateTime.parse("2023-06-09T15:25:30"),LocalDateTime.parse("2023-06-09T16:05:30"),"Norwegian\nAir International", 130, 220, cities.getCity("Oslo"),cities.getCity("London"), "London", "Oslo");
        Flight f9 = new Flight("LH 3470", LocalDateTime.parse("2023-02-05T18:25:30"),LocalDateTime.parse("2023-02-05T20:00:30"),"EasyJet", 130, 220,cities.getCity("Prague"), cities.getCity("London"), "London", "Prague");
        Flight f10 = new Flight("LH 0107", LocalDateTime.parse("2023-07-08T06:45:30"),LocalDateTime.parse("2023-07-08T10:45:30"),"Lufthansa", 460, 700,cities.getCity("Havana"),cities.getCity("Frankfurt"), "Frankfurt", "Havana");
        Flight f11 = new Flight("LO 2138", LocalDateTime.parse("2023-08-05T10:45:30"),LocalDateTime.parse("2023-08-05T16:45:30"),"LOT", 130, 220,cities.getCity("Warsaw"),cities.getCity("Berlin"), "Berlin", "Warsaw");
        Flight f12 = new Flight("W9 4767", LocalDateTime.parse("2023-02-05T17:50:30"),LocalDateTime.parse("2023-02-05T19:45:30"),"Wizz Air UK", 130, 220,cities.getCity("Glasgow"), cities.getCity("Munich"), "Munich", "Glasgow");
        Flight f13 = new Flight("LH 0007", LocalDateTime.parse("2023-08-05T12:00:30"),LocalDateTime.parse("2023-08-05T12:45:30"),"Lufthansa", 130, 220, cities.getCity("Hamburg"), cities.getCity("Munich"), "Munich", "Hamburg");
        Flight f14 = new Flight("BT 2179", LocalDateTime.parse("2023-09-05T16:05:30"),LocalDateTime.parse("2023-09-05T18:15:30"),"Air Baltic", 130, 220, cities.getCity("Riga"), cities.getCity("Paris"), "Paris", "Riga");
        Flight f15 = new Flight("LO 2139", LocalDateTime.parse("2023-02-05T08:30:30"),LocalDateTime.parse("2023-02-05T10:45:30"),"LOT", 130, 220, cities.getCity("Katowice"), cities.getCity("Barcelona"), "Barcelona", "Katowice");
        Flight f2 = new Flight("AY 6677", LocalDateTime.parse("2023-01-05T10:00:00"),LocalDateTime.parse("2023-01-05T12:45:30"),"Finnair", 130, 220,cities.getCity("Helsinki"), cities.getCity("Warsaw"), "Warsaw", "Helsinki");
        List<Flight> list = new ArrayList<>();
        list.add(f1);
        list.add(f3);
        list.add(f4);
        list.add(f5);
        list.add(f6);
        list.add(f7);
        list.add(f8);
        list.add(f9);
        list.add(f10);
        list.add(f11);
        list.add(f12);
        list.add(f13);
        list.add(f14);
        list.add(f15);
        list.add(f2);
        return list;
    }


    public List<Flight> getFlights() {
        return flights;
    }

}
