package com.example.airline_api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String destination;
    @Column
    private int capacity;
    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;
    @ManyToMany(mappedBy = "flights")
    @JsonIgnoreProperties({"flights"})
    private List<Passenger> passengers;

    public Flight(String destination, int capacity, LocalDateTime departureDateTime) {
        this.destination = destination;
        this.capacity = capacity;
        this.departureDateTime = departureDateTime;
        this.passengers = new ArrayList<>();
    }

    public Flight() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getDepartureDateTime() {
        return this.departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void removePassenger(Passenger passenger){
        this.passengers.remove(passenger);
    }
}
