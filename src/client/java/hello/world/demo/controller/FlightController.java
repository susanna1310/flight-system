package hello.world.demo.controller;


import hello.world.demo.model.Flight;
import hello.world.demo.model.MyFlightSortingOption;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FlightController {

    private final WebClient webClient;
    private final List<Flight> flights;

    public FlightController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight, Consumer<List<Flight>> flightConsumer) {
        webClient.post()
                .uri("flights")
                .bodyValue(flight)
                .retrieve()
                .bodyToMono(Flight.class)
                .onErrorStop()
                .subscribe(newFlight -> {
                    flights.add(newFlight);
                    flightConsumer.accept(flights);
                });
    }

    public void updateFlight(Flight flight, Consumer<List<Flight>> flightsConsumer) {
        webClient.put()
                .uri("flights/" + flight.getFlight())
                .bodyValue(flight)
                .retrieve()
                .bodyToMono(Flight.class)
                .onErrorStop()
                .subscribe(newFlight -> {
                    flights.replaceAll(oldFlight -> oldFlight.getFlight().equals(newFlight.getFlight()) ? newFlight : oldFlight);
                    flightsConsumer.accept(flights);
                });
    }

    public void deleteFlight(Flight flight, Consumer<List<Flight>> flightsConsumer) {
        webClient.delete()
                .uri("flights/" + flight.getFlight())
                .retrieve()
                .toBodilessEntity()
                .onErrorStop()
                .subscribe(v -> {
                    flights.remove(flight);
                    flightsConsumer.accept(flights);
                });
    }

    public void getAllFlights(MyFlightSortingOption sortingOption, Consumer<List<Flight>> flightsConsumer) {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("flights")
                        .queryParam("sortingOption", sortingOption)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Flight>>() {})
                .onErrorStop()
                .subscribe(newPerson -> {
                    flights.clear();
                    flights.addAll(newPerson);
                    flightsConsumer.accept(flights);
                });
    }
}
