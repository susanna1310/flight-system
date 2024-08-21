package hello.world.demo.rest;


import hello.world.demo.model.Flight;
import hello.world.demo.model.MyFlightSortingOption;
import hello.world.demo.service.FlightService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FlightResource {

    private final FlightService flightService;

    public FlightResource(FlightService flightService) {
        this.flightService = flightService;
    }


    @PostMapping("flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.saveFlight(flight));
    }

    @PutMapping("flights/{flightNumber}")
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight updatedFlight, @PathVariable("flightNumber") String flightNumber) {
        if (!updatedFlight.getFlight().equals(flightNumber)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(flightService.saveFlight(updatedFlight));
    }

    @DeleteMapping("flights/{flightNumber}")
    public ResponseEntity<Void> deleteFlight(@PathVariable("flightNumber") String flightNumber) {
        flightService.deleteFlight(flightNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("flights")
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(value = "sortingOption", defaultValue = "UPCOMING_FLIGHTS")MyFlightSortingOption sortingOption) {
        return ResponseEntity.ok(flightService.getAllFlights(sortingOption));
    }

}
