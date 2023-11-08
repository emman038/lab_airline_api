package com.example.airline_api.services;

import com.example.airline_api.dtos.PassengerDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PassengerRepository passengerRepository;

    public Flight addFlight(Flight flight){
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public List<Flight> getFlightByDestination(String destination){
        return flightRepository.findByDestination(destination);
    }

    public boolean isFlightFull(Long id){
        Flight flight = flightRepository.findById(id).get();
        int capacity = flight.getCapacity();
        int passengersOnboard = flight.getPassengers().size();

        if (passengersOnboard < capacity){
            return false;
        }

        return true;
    }

    public Flight addPassengerToFlight(Long id, PassengerDTO passengerDTO) {
        Passenger passenger = passengerRepository.findById(passengerDTO.getPassengerId()).get();
        Flight flight = flightRepository.findById(id).get();
        passenger.addFlight(flight);
        passengerRepository.save(passenger);

        return flightRepository.findById(id).get();
    }

    @Transactional
    public void cancelFlight(Long id){
        // Find Flight
        Optional<Flight> checkFlight = flightRepository.findById(id);

        // Check if Flight is present
        if (checkFlight.isPresent()){
            Flight flight = checkFlight.get();

            // Remove flight from passenger
            for (Passenger passenger : flight.getPassengers()){
                passenger.removeFlight(flight);
            }

            // save updated flight
            flightRepository.save(flight);

            // delete flight
            flightRepository.deleteById(id);
        }
    }

}
