package com.example.airline_api.services;

import com.example.airline_api.dtos.PassengerDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
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

    public Optional<Flight> addPassengerToFlight(Long id, PassengerDTO passengerDTO) {
        Optional<Passenger> checkPassenger = passengerRepository.findById(passengerDTO.getPassengerId());
        Optional<Flight> checkFlight = flightRepository.findById(id);
        if (checkPassenger.isPresent() && checkFlight.isPresent()){
            Passenger passenger = checkPassenger.get();
            Flight flight = checkFlight.get();

            List<Passenger> passengers = flight.getPassengers();
            passengers.add(passenger);

            flight.setPassengers(passengers);

            flightRepository.save(flight);
        }
        return flightRepository.findById(id);
    }
}
