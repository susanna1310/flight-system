package hello.world.demo.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Flight {
    private String flight;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String airline;
    private double economyPrice;
    private double businessPrice;
    private City cityOfDestination;
    private City cityOfDeparture;
    private String cityDep;
    private String cityDes;
    private String arrival;
    private String departure;
    private Feedback feedback;

    public Flight() {}

    public Flight(String flight, LocalDateTime departureDate, LocalDateTime arrivalDate,
                  String airline, double economyPrice, double businessPrice, City cityOfDestination, City cityOfDeparture, String cityDep, String cityDes){
        this.flight = flight;
        this.airline=airline;
        this.economyPrice=economyPrice;
        this.businessPrice=businessPrice;
        this.cityOfDestination=cityOfDestination;
        this.cityOfDeparture=cityOfDeparture;
        this.cityDep = cityDep;
        this.cityDes = cityDes;
        this.departureDate=departureDate;;
        this.arrivalDate=arrivalDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy \nHH:mma");
        this.arrival = arrivalDate.format(formatter);
        this.departure = departureDate.format(formatter);
        this.feedback = new Feedback();
    }




    public String getCityDep() {
        return cityDep;
    }

    public String getCityDes() {
        return cityDes;
    }

    public String getFlight() {
        return flight;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public String getAirline() {
        return airline;
    }

    public double getEconomyPrice() {
        return economyPrice;
    }

    public double getBusinessPrice() {
        return businessPrice;
    }

    public City getCityOfDestination() {
        return cityOfDestination;
    }



    public City getCityOfDeparture() {
        return cityOfDeparture;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setEconomyPrice(double economyPrice) {
        this.economyPrice = economyPrice;
    }

    public void setBusinessPrice(double businessPrice) {
        this.businessPrice = businessPrice;
    }

    public void setCityOfDestination(City cityOfDestination) {
        this.cityOfDestination = cityOfDestination;
    }

    public void setCityOfDeparture(City cityOfDeparture) {
        this.cityOfDeparture = cityOfDeparture;
    }

    public void setCityDep(String cityDep) {
        this.cityDep = cityDep;
    }

    public void setCityDes(String cityDes) {
        this.cityDes = cityDes;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public Feedback getFeedback() {
        return feedback;
    }
}
