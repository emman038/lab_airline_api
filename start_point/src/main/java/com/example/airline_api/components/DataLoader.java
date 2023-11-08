package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passengerService;

    public void run(ApplicationArguments args){
        Passenger passenger1 = new Passenger("Emmanuel", "emmanuel380@yahoo.com");
        Passenger passenger2 = new Passenger("Samuel", "samuel@gmail.com");
        Passenger passenger3 = new Passenger("Ewan", "ewan@google.co.uk");
        Passenger passenger4 = new Passenger("Joseph", "joseph@email.com");
        Passenger passenger5 = new Passenger("Daniel", "daniel@live.co.uk");
        Passenger passenger6 = new Passenger("Peter", "peter@live.co.uk");
        Passenger passenger7 = new Passenger("Ali", "ali@gmail.co.uk");

        passengerService.addNewPassenger(passenger1);
        passengerService.addNewPassenger(passenger2);
        passengerService.addNewPassenger(passenger3);
        passengerService.addNewPassenger(passenger4);
        passengerService.addNewPassenger(passenger5);
        passengerService.addNewPassenger(passenger6);
        passengerService.addNewPassenger(passenger7);

        Flight flight1 = new Flight("London", 300, "20/11/2023", "10:30");
        Flight flight2 = new Flight("China", 150, "25/12/2023", "12:30");
        Flight flight3 = new Flight("Portugal", 400, "01/01/2024", "16:30");

        flightService.addFlight(flight1);
        flightService.addFlight(flight2);
        flightService.addFlight(flight3);
    }
}
