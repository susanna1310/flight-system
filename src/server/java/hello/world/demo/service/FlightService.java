package hello.world.demo.service;

import hello.world.demo.model.Flight;
import hello.world.demo.model.MyFlightSortingOption;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final List<Flight> flights;

    public FlightService() {
        this.flights = new ArrayList<>();
    }


    public Flight saveFlight(Flight flight) {
        var optionalFlight = flights.stream().filter(existingFlight -> existingFlight.getFlight().equals(flight.getFlight())).findFirst();
        if (optionalFlight.isEmpty()) {
            flights.add(flight);
            return flight;
        } else {
            var existingFlight = optionalFlight.get();
            existingFlight.setAirline(flight.getAirline());
            existingFlight.setArrivalDate(flight.getArrivalDate());
            existingFlight.setDepartureDate(flight.getDepartureDate());
            existingFlight.setBusinessPrice(flight.getBusinessPrice());
            existingFlight.setEconomyPrice(flight.getEconomyPrice());
            existingFlight.setCityDep(flight.getCityDep());
            existingFlight.setCityDes(flight.getCityDes());
            existingFlight.setCityOfDeparture(flight.getCityOfDeparture());
            existingFlight.setCityOfDestination(flight.getCityOfDestination());
            return existingFlight;
        }
    }

    public void deleteFlight(String flightNumber) {
        this.flights.removeIf(flight -> flight.getFlight().equals(flightNumber));
    }

    public List<Flight> getAllFlights(MyFlightSortingOption option) {
        List<Flight> list = new ArrayList<>();
        if (option.equals(MyFlightSortingOption.PAST_FLIGHTS)) {
           list = this.flights.stream().filter(flight -> flight.getArrivalDate().isBefore(LocalDateTime.now())).collect(Collectors.toList());
        } else if (option.equals(MyFlightSortingOption.UPCOMING_FLIGHTS)) {
            list = this.flights.stream().filter(flight -> flight.getArrivalDate().isAfter(LocalDateTime.now())).collect(Collectors.toList());
        } else if (option.equals(MyFlightSortingOption.ALL_FLIGHTS)) {
            return Collections.unmodifiableList(this.flights);
        }
        return list;
    }





}
