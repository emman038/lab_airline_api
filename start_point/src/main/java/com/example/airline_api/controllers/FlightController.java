package com.example.airline_api.controllers;

import com.example.airline_api.dtos.PassengerDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(required = false) String destination){
        if (destination != null){
            return new ResponseEntity<>(flightService.getFlightByDestination(destination), HttpStatus.OK);
        }
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
        Optional<Flight> checkFlight = flightService.getFlightById(id);
        if (checkFlight.isPresent()){
            return new ResponseEntity<>(checkFlight.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody Flight flight){
        return new ResponseEntity<>(flightService.addFlight(flight), HttpStatus.CREATED);
    }

    // Book passenger on a flight
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable Long id, @RequestBody PassengerDTO passengerDTO){
        // Validation
        Optional<Flight> checkFlight = flightService.getFlightById(id);
        if (checkFlight.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (!flightService.isFlightFull(id)) {
            return new ResponseEntity<>(flightService.addPassengerToFlight(id, passengerDTO), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);

    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> cancelFlight(@PathVariable Long id){
        flightService.cancelFlight(id);

        // Validation to see if flight was cancelled (if method worked)
        if (flightService.getFlightById(id).isEmpty()){
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
