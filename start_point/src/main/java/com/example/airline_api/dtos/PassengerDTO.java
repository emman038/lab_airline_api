package com.example.airline_api.dtos;

public class PassengerDTO {
    private Long passengerId;

    public PassengerDTO(Long passengerId) {
        this.passengerId = passengerId;
    }

    public PassengerDTO() {
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

}
