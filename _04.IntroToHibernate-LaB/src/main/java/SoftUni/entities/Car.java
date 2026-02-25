package SoftUni.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CAR")

public class Car extends Vehicle {

    private int passengers;

    public Car() //Default Constructor
    {}

    public Car(int passengers) {
        this.passengers = passengers;
    }

    public int getPassengers() {
        return passengers;
    }
}

